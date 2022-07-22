package run.codeis.apitest.application;

import org.springframework.stereotype.Service;
import run.codeis.apitest.domain.socket.SocketDto;
import run.codeis.apitest.infrastructure.SocketUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author liubinqiang
 * @date 2022-7-20
 */
@Service
public class SocketService {

	/**
	 * CountDownLatch方式
	 *
	 * @param socketDto
	 * @return
	 * @throws InterruptedException
	 */
	public List<SocketDto> doSocket(SocketDto socketDto) throws InterruptedException {
		List<SocketDto> list = new ArrayList<>();
		List<Thread> threadList = new ArrayList<>();
		CountDownLatch countDownLatch = new CountDownLatch(socketDto.getParallelNum());
		for (int i = 0; i < socketDto.getParallelNum(); i++) {
			SocketDto socketDtoTemp = socketDto.clone();
			Thread thread = new Thread(() -> {
				SocketDto result = SocketUtil.doSocket(socketDtoTemp);
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

	/**
	 * Callable/FutureTask方式
	 *
	 * @param socketDto
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public List<SocketDto> doSocket1(SocketDto socketDto) throws InterruptedException, ExecutionException {
		List<SocketDto> list = new ArrayList<>();
		List<FutureTask<SocketDto>> futureTaskList = new ArrayList<>();
		for (int i = 0; i < socketDto.getParallelNum(); i++) {
			SocketDto socketDtoTemp = socketDto.clone();
			SocketCallable socketCallable = new SocketCallable(socketDtoTemp);
			FutureTask<SocketDto> futureTask = new FutureTask<>(socketCallable);
			futureTaskList.add(futureTask);
			new Thread(futureTask, "do-socket-" + i).start();
		}
		for (FutureTask<SocketDto> futureTask : futureTaskList) {
			list.add(futureTask.get());
		}
		return list;
	}

	static class SocketCallable implements Callable<SocketDto> {
		private SocketDto socketDto;

		public SocketCallable(SocketDto socketDto) {
			this.socketDto = socketDto;
		}

		@Override
		public SocketDto call() throws Exception {
			return SocketUtil.doSocket(socketDto);
		}
	}

}
