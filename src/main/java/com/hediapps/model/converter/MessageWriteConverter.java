package com.hediapps.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hediapps.model.Message;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Component
public class MessageWriteConverter implements Converter<Message, DBObject> {

	public DBObject convert(Message source) {

		DBObject dbo = new BasicDBObject();
		dbo.put("_id", source.getId());
		dbo.put("_class", Message.class.getName());

		dbo.put("fromUser", source.getFromUser());
		dbo.put("toUser", source.getToUser());
		//dbo.put("creationDate", source.getCreationDate() == null ? 0L : source.getCreationDate().getTime());
		dbo.put("subject", source.getSubject());
		dbo.put("text", source.getText());

		return dbo;
	}
}