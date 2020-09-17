package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import solver.Answer;
import solver.Conversation;
import solver.utils.ConversationUtil;

class ConversationTest {

	@Test
	void notZeroTest() {
		Conversation c=new Conversation();
		String first = c.getCurrentCode();
		c.setAnswerReady(ConversationUtil.fromStringToAnswer("Lavastoviglie", c.getAnswersList()));
		c.nextQuestion();
		assertTrue(c.getCurrentCode()!= null);
		assertTrue(first != c.getCurrentCode());
	}
	
	@Test
	void notRecoursiveTest() {
		Conversation c=new Conversation();
		String first = c.getCurrentCode();
		c.setAnswerReady(ConversationUtil.fromStringToAnswer("Lavastoviglie", c.getAnswersList()));
		c.nextQuestion();
		assertTrue(first.length() < c.getCurrentCode().length());
	}

}
