package com.hediapps.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
	
	private UserDto fromUser;
	private String destination;
	private long creationDate;
	private String text;
	private boolean read;
}
