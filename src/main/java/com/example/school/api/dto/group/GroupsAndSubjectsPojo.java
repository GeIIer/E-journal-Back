package com.example.school.api.dto.group;

import com.example.school.api.dto.SubjectPojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupsAndSubjectsPojo {
    private List<GroupWithoutStudentsPojo> groups;
    private List<SubjectPojo> subjects;
}
