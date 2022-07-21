package run.codeis.apitest.application;

import org.springframework.stereotype.Service;
import run.codeis.apitest.domain.socket.SocketDto;
import run.codeis.apitest.infrastructure.SocketUtil;

import java.io.IOException;

/**
 * @author liubinqiang
 * @date 2022-7-20
 */
@Service
public class SocketService {
	public SocketDto doSocket(SocketDto socketDto) throws IOException {
		return SocketUtil.doSocket(socketDto);
	}
}
