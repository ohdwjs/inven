package first.sample.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.common.common.CommandMap;
import first.sample.service.SampleService;

@Controller
public class SampleController {
	//로그확인용
    Logger log = Logger.getLogger(this.getClass());
    
    @Resource(name="sampleService")
    private SampleService sampleService;
    
    @RequestMapping(value="/sample/openSampleBoardList.do")
    public ModelAndView openSampleBoardList(Map<String, Object> commandMap) throws Exception{
		log.debug("join");
    	ModelAndView mv= new ModelAndView("/sample/boardList");
    	List<Map<String, Object>> list = sampleService.selectBoardList(commandMap);
    	mv.addObject("list", list);
    	return mv;
    }
    //파라미터 값을 가져올 때 편하게 가져오는 코드
    @RequestMapping(value="/sample/testMapArgumentResolver.do")
    public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception{
    	ModelAndView mv= new ModelAndView("");
    	
    	if(commandMap.isEmpty() ==false){
    		Iterator<Entry<String,Object>> iterator=commandMap.getMap().entrySet().iterator();
    		Entry<String,Object> entry =null;
    		while(iterator.hasNext()){
    		entry = iterator.next();
    		log.debug("key : "+entry.getKey()+", value : "+entry.getValue());
    		}
    	}
    	return mv;
    }
    
}
