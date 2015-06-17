package br.com.autodoc.fila;

import java.io.Serializable;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Fila {

	private final String QUEUE_LOOKUP = "queue/";
	private final String CONNECTION_FACTORY = "ConnectionFactory";
	private String nomeFila;
	

	public Fila(String nomeFila) {

		this.nomeFila = nomeFila;
	}

	public void add(Serializable object) {

		String fila = QUEUE_LOOKUP+nomeFila;
		try {
			Context context = new InitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory) context
					.lookup(CONNECTION_FACTORY);
			QueueConnection connection = factory.createQueueConnection();
			QueueSession session = connection.createQueueSession(false,
					QueueSession.AUTO_ACKNOWLEDGE);

			Queue queue = (Queue) context.lookup(fila);
			
			System.out.println("Adicionado a fila :"+fila);
			
			QueueSender sender = session.createSender(queue);

			ObjectMessage objMsg = session.createObjectMessage();
			
			System.out.println("object "+object);
			objMsg.setObject(object);
			sender.send(objMsg);
						
			session.close();
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
