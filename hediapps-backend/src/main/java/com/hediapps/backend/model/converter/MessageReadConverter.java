package com.hediapps.backend.model.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hediapps.backend.model.Email;
import com.hediapps.backend.model.Message;
import com.mongodb.DBObject;

@Component
public class MessageReadConverter implements Converter<DBObject, Message> {

	public Message convert(DBObject source) {

		if (source.get("_class").toString().equals(Email.class.getName())) {
			Email msg = new Email();
			msg.setId((Long) source.get("_id"));
			msg.setFromUser((Long) source.get("fromUser"));
			msg.setToUser((Long) source.get("toUser"));
			msg.setCreationDate(new Date((Long) source.get("creationDate")));
			msg.setSubject(source.get("subject").toString());
			msg.setText(source.get("text").toString());
			return msg;
		}

		else {
			return null;
		}
	}
}