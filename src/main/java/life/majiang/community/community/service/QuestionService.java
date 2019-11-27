package life.majiang.community.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.User;

@Service
public class QuestionService {
		@Autowired
		UserMapper userMapper;
		@Autowired
		QuestionMapper questionMapper;
		public List<QuestionDTO> list(){
			List<QuestionDTO> questionDTOList = new ArrayList<>();
			List<Question> questions = questionMapper.list();
			for(Question question : questions){
				User user = userMapper.findById(question.getCreator());
				QuestionDTO questionDTO = new QuestionDTO();
				BeanUtils.copyProperties(question, questionDTO);
				questionDTO.setUser(user);
				questionDTOList.add(questionDTO);
			}
			return questionDTOList;
		}
}
