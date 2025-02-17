package com.base.vm.controller;

import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Tag(name = "文件上传下载")
@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController extends ResultUtil {

    // 定义文件上传的目录
    private static final String UPLOAD_DIR = "uploads";

    /**
     * 文件上传接口
     * @param file 上传的文件
     * @return 上传结果
     */
    @Operation(summary = "文件上传")
    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 检查文件是否为空
            if (file.isEmpty()) {
                throw new BadRequestException("上传的文件为空");
            }

            // 获取文件名
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            // 构建文件保存路径
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 保存文件
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return success(true, "文件上传成功");
        } catch (IOException e) {
            return fail(false, "文件上传失败");
        }
    }

    /**
     * 多文件上传接口，支持传入其他参数
     * @param files 上传的文件列表
     * @param param 其他参数
     * @return 上传结果
     */
    @Operation(summary = "多文件上传")
    @PostMapping("/uploadMultiple")
    public ResponseEntity<Object> uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files, @RequestParam("param") String param) {
        try {
            if (files.isEmpty()) {
                throw new BadRequestException("上传的文件列表为空");
            }

            // 构建文件保存路径
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            return success(true, "多文件上传成功，参数: " + param);
        } catch (IOException e) {
            return fail(false, "多文件上传失败");
        }
    }

    /**
     * 文件下载接口
     * @param fileName 要下载的文件名
     * @return 文件资源
     */
    @Operation(summary = "文件下载")
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            // 构建文件路径
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            // 检查文件是否存在
            if (resource.exists() && resource.isReadable()) {
                // 设置响应头
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } else {
                throw new BadRequestException("文件不存在或无法读取");
            }
        } catch (MalformedURLException e) {
            throw new BadRequestException("文件下载失败");
        }
    }

    /**
     * 分块文件下载接口
     * @param fileName 要下载的文件名
     * @param rangeHeader 客户端发送的 Range 请求头
     * @return 文件的分块资源
     */
    @Operation(summary = "分块文件下载")
    @GetMapping("/download/range/{fileName}")
    public ResponseEntity<Resource> downloadFileRange(@PathVariable String fileName, @RequestHeader(value = "Range", required = false) String rangeHeader) {
        try {
            // 构建文件路径
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            // 检查文件是否存在
            if (!resource.exists() || !resource.isReadable()) {
                throw new BadRequestException("文件不存在或无法读取");
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);

            long fileSize = Files.size(filePath);

            if (rangeHeader == null) {
                // 完整下载
                headers.setContentLength(fileSize);
                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            }

            // 分块下载
            String[] range = rangeHeader.substring("bytes=".length()).split("-");
            long start = Long.parseLong(range[0]);
            long end = range.length > 1 ? Long.parseLong(range[1]) : fileSize - 1;
            long contentLength = end - start + 1;

            headers.set("Accept-Ranges", "bytes");
            headers.setContentLength(contentLength);
            headers.set("Content-Range", "bytes " + start + "-" + end + "/" + fileSize);

            byte[] content = Files.readAllBytes(filePath);
            byte[] chunk = new byte[(int) contentLength];
            System.arraycopy(content, (int) start, chunk, 0, (int) contentLength);

            return new ResponseEntity<>(new org.springframework.core.io.ByteArrayResource(chunk), headers, HttpStatus.PARTIAL_CONTENT);
        } catch (MalformedURLException e) {
            throw new BadRequestException("文件下载失败");
        } catch (IOException e) {
            throw new BadRequestException("读取文件时出错");
        }
    }
}