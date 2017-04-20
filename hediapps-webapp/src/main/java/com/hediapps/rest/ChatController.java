package com.hediapps.rest;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@MessageMapping("/add")
	@SendTo("/topic/showResult")
	public Result addNum(CalcInput input) throws Exception {
		System.out.println(input.getNum1());
		Result result = new Result(input.getNum1() + "+" + input.getNum2() + "=" + (input.getNum1() + input.getNum2()));
		return result;
	}

	@RequestMapping("/start")
	public String start() {
		return "start";
	}
}