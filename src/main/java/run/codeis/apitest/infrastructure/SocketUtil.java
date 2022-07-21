package run.codeis.apitest.infrastructure;

import lombok.extern.slf4j.Slf4j;
import run.codeis.apitest.domain.socket.SocketDto;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author liubinqiang
 * @date 2022-6-29
 */
@Slf4j
public class SocketUtil {


	public static SocketDto doSocket(SocketDto socketDto) throws IOException {
		Socket socket = new Socket(socketDto.getIp(), socketDto.getPort());
		//发送数据
		write(socket, socketDto.getRequest());
		//socket.shutdownOutput();

		String resp = read(socket);
		socket.shutdownInput();
		socketDto.setResponse(resp);
		return socketDto;
	}

	private static void write(Socket socket, String message) {
		message = message + "\n";
		log.info("准备发送数据：\n{}", message);
		try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
			BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
			bufferedWriter.write(message);
			bufferedWriter.flush();
		} catch (IOException ex) {
			log.error("返回socket报错", ex);
		}
	}

	private static String read(Socket socket) {
		log.info("开始读取socket输入数据");
		StringBuilder inputStr = new StringBuilder();
		try {
			socket.setSoTimeout(10000);
			//关闭inputStreamReader、bufferedReader后，后面返回报Socket is closed
			InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line = null;
			log.info("读取socket输入数据：bufferedReader.ready()={}", bufferedReader.ready());
			//等待读取准备好
			for (int i = 0; i < 10; i++) {
				log.info("等待bufferedReader准备好：bufferedReader.ready()={}", bufferedReader.ready());
				if (bufferedReader.ready()) {
					break;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					log.error("等待读取准备好报错", e);
				}
			}
			while (bufferedReader.ready() && (line = bufferedReader.readLine()) != null) {
				inputStr.append(line).append("\n");
				log.info("读取行，已读长度={}，行数据：{}", inputStr.length(), line);
			}
			log.info("读取socket输入数据完成：bufferedReader.ready()={},已读长度={}，行数据：{}", bufferedReader.ready(), inputStr.length(), line);
			return inputStr.toString();
		} catch (IOException ex) {
			log.error("读取socket输入报错", ex);
			return inputStr.toString();
		}
	}


}
