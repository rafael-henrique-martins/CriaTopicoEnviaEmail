package com.examploSns.demo.service.serviceImpl;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.examploSns.demo.service.SNSService;
import org.springframework.stereotype.Service;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;

@Service
public class SNSServiceimpl implements SNSService {

    private static final String TOPIC_ARN_EMAIL = "arn:aws:sns:us-east-1";
    private static final String EMAIL_SUBJECT = "Teste de envio de Email";
    private static final String EMAIL_MESSAGE = "Mensagem do corpo do email \n\n\n\n\n\n\n";

    private static AmazonSNSClient snsClient = null;

    public String publishMessageToTopic(String email) {
//      criando o client SNS
        snsClient = (AmazonSNSClient) AmazonSNSClientBuilder.standard().build();

//      criando um TOPICO no SNS
		final CreateTopicRequest createTopicRequest = new CreateTopicRequest("PrimeiroTopicoComJava");
		final CreateTopicResult createTopicResponse = snsClient.createTopic(createTopicRequest);

//		Print do ARN do TOPICO
		String TOPIC_ARN_FROM_JAVA = createTopicResponse.getTopicArn();
		System.out.println("TopicArn:" + TOPIC_ARN_FROM_JAVA);

//		Print o ID da solicitação para a ação CreateTopicRequest
		System.out.println("CreateTopicRequest: " + snsClient.getCachedResponseMetadata(createTopicRequest));

		final SubscribeRequest subscribeRequest = new SubscribeRequest(TOPIC_ARN_FROM_JAVA, "email", "xxx@hotmail.com");
		snsClient.subscribe(subscribeRequest);

//      publicando uma mensagem no TOPICO
//		snsClient.publish(TOPIC_ARN_EMAIL, EMAIL_MESSAGE, EMAIL_SUBJECT);

        return "Sucessoo!!";
    }
}
