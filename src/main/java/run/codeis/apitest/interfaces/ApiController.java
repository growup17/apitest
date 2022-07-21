package run.codeis.apitest.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import run.codeis.apitest.application.SocketService;
import run.codeis.apitest.domain.socket.SocketDto;

import java.io.IOException;

/**
 * 提供测试接口
 *
 * @author liubinqiang
 * @date 2022-7-20
 */
@Controller
public class ApiController {

	@Autowired
	private SocketService socketService;

	@RequestMapping("/test")
	public String testPage() {
		return "testPage";
	}

	@ResponseBody
	@RequestMapping("/doSocket")
	public SocketDto doSocket(SocketDto socketDto) throws IOException {
		return socketService.doSocket(socketDto);
	}
}
