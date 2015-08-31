package stockexchange.utils;

import java.util.List;

import stockexchange.model.Investment;

public class ListUtils {

	public Long getNextId(List<Investment> list){
		Long max = 0L;
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getId() > max){
				max = list.get(i).getId();
			}
		}
		return max + 1;
	}
}
