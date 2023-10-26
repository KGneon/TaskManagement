package com.team.validation;

import com.team.exception.TaskManagementException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskAndUserValidation {

    public static void validateId(Integer id){
        if(!checkingIdIsNullOrNegative(id)) throw new TaskManagementException("Service.ID_NEGATIVE_OR_NULL");
    }
    public static void validateListOfIds(List<Integer> listOfIds) {
        if(!checkingIdListIsNullOrEmpty(listOfIds)) throw new TaskManagementException("Service.NO_IDS_GIVEN");
        if(!checkingIdListDuplicates(listOfIds)) throw new TaskManagementException("Service.IDS_DUPLICATES_EXISTS");
    }
    public static void validateListDuplicates(List<Integer> listOfIds) {
        if(!checkingIdListDuplicates(listOfIds)) throw new TaskManagementException("Service.IDS_DUPLICATES_EXISTS");
    }
    static Boolean checkingIdListIsNullOrEmpty(List<Integer> listOfIds) {
        return listOfIds == null || listOfIds.isEmpty();
    }
    static Boolean checkingIdListDuplicates(List<Integer> listOfIds) {
        Set<Integer> noDuplicatesSet = new HashSet<>();
        for (Integer id : listOfIds) {
            if (!noDuplicatesSet.add(id)) {
                return false;
            }
        }
        return true;
    }
    static Boolean checkingIdIsNullOrNegative(Integer id){
        return (id == null || id <= 0);
    }
}
