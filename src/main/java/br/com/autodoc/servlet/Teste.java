package br.com.autodoc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.autodoc.entity.*;

/**
 * Servlet implementation class teste
 */
@WebServlet("/teste")
public class Teste extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Email mensagem;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Teste() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String QUEUE_LOOKUP = "queue/Email";
		final String CONNECTION_FACTORY = "ConnectionFactory";

		PrintWriter out = response.getWriter();
		try {
			Context context = new InitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory) context
					.lookup(CONNECTION_FACTORY);
			QueueConnection connection = factory.createQueueConnection();
			QueueSession session = connection.createQueueSession(false,
					QueueSession.AUTO_ACKNOWLEDGE);

			Queue queue = (Queue) context.lookup(QUEUE_LOOKUP);
			QueueSender sender = session.createSender(queue);

			

			// 2. Enviando objeto ObjectMessage
			ObjectMessage objMsg = session.createObjectMessage();
			mensagem = new Email();
			mensagem.setFrom("teste");

			objMsg.setObject(mensagem);
			sender.send(objMsg);
			out.println("2. Enviando mensagem ObjectMessage");
			
			
			// 1. Enviando objeto TextMessage
						TextMessage message = session.createTextMessage();
						message.setText("Exemplo EJB3 MDB Queue!!!");
						message.setLongProperty("_HQ_SCHED_DELIVERY", System.currentTimeMillis() + 50000);
						sender.send(message);
						out.println("1. Enviando mensagem tipo TextMessage");
						
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
