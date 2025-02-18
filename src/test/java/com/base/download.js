const https = require('https');
const fs = require('fs');
const path = require('path');

// 下载单个文件
function downloadFile(url, outputPath) {
    return new Promise((resolve, reject) => {
        https.get(url, (res) => {
            const fileStream = fs.createWriteStream(outputPath);
            res.pipe(fileStream);

            fileStream.on('finish', () => {
                fileStream.close();
                resolve();
            });

            fileStream.on('error', (err) => {
                reject(err);
            });
        }).on('error', (err) => {
            reject(err);
        });
    });
}

// 合并文件
function mergeFiles(filePaths, outputPath) {
    return new Promise((resolve, reject) => {
        const outputStream = fs.createWriteStream(outputPath);

        function appendNextFile(index) {
            if (index >= filePaths.length) {
                outputStream.end();
                resolve();
                return;
            }

            const inputStream = fs.createReadStream(filePaths[index]);
            inputStream.pipe(outputStream, { end: false });

            inputStream.on('end', () => {
                appendNextFile(index + 1);
            });

            inputStream.on('error', (err) => {
                reject(err);
            });
        }

        appendNextFile(0);
    });
}

// 删除临时文件
function deleteFiles(filePaths) {
    filePaths.forEach((filePath) => {
        if (fs.existsSync(filePath)) {
            fs.unlinkSync(filePath);
        }
    });
}

async function downloadAndMergeVideos(baseUrl, name, count) {
    const tempFiles = [];
    try {
        // 下载所有视频片段
        for (let i = 0; i < count; i++) {
            const url = `${baseUrl}${name}-${i}.m4s`;
            const tempFilePath = path.join(__dirname, `temp_${i}.m4s`);
            tempFiles.push(tempFilePath);
            await downloadFile(url, tempFilePath);
            console.log(`下载完成: ${url}`);
        }

        // 合并视频片段
        const outputPath = path.join(__dirname, 'merged_video.m4s');
        await mergeFiles(tempFiles, outputPath);
        console.log('视频合并完成');

        // 删除临时文件
        deleteFiles(tempFiles);
        console.log('临时文件已删除');
    } catch (error) {
        console.error('发生错误:', error);
        // 出现错误时也尝试删除临时文件
        deleteFiles(tempFiles);
    }
}

// 使用示例
const baseUrl = 'https://example.com/'; // 替换为实际的基础 URL
const name = 'name'; // 替换为实际的文件名前缀
const count = 125; // 视频片段的数量

downloadAndMergeVideos(baseUrl, name, count);

// find . -name "*.ts" -exec echo "file '{}'" \; > list.txt # Linux 或 macOS 系统中将文件名称存入文件
// ffmpeg -f concat -safe 0 -i list.txt -c copy output.mp4