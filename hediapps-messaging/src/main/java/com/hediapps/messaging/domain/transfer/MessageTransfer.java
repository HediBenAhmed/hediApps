package com.hediapps.messaging.domain.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageTransfer {
	
	private UserTransfer fromUser;
	private String destination;
	private long creationDate;
	private String text;
	private boolean read;
}
