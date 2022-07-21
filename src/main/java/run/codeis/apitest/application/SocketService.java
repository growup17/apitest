package run.codeis.apitest.application;

import org.springframework.stereotype.Service;
import run.codeis.apitest.domain.socket.SocketDto;
import run.codeis.apitest.infrastructure.SocketUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author liubinqiang
 * @date 2022-7-20
 */
@Service
public class SocketService {
	public List<SocketDto> doSocket(SocketDto socketDto) throws InterruptedException {
		List<SocketDto> list = new ArrayList<>();
		List<Thread> threadList = new ArrayList<>();
		CountDownLatch countDownLatch = new CountDownLatch(socketDto.getParallelNum());
		for (int i = 0; i < socketDto.getParallelNum(); i++) {
			Thread thread = new Thread(() -> {
				SocketDto result = SocketUtil.doSocket(socketDto);
				list.add(result);
				countDownLatch.countDown();
			}, "do-socket-" + i);
			threadList.add(thread);
		}
		for (Thread thread : threadList) {
			thread.start();
		}
		countDownLatch.await();
		return list;
	}
}
