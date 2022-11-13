//package cz.cvut.kbss.ear.mroom.model;
//
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@Entity
//@Table
//public class Week extends AbstractEntity{
//
//    @OneToOne(mappedBy = "week")
//    @JoinColumn(nullable = false)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private StudyRoom room;
//
//    @OneToMany(mappedBy = "week", orphanRemoval = true, cascade = CascadeType.ALL)
//    @MapKeyEnumerated(EnumType.STRING)
//    private Map<DaysOfWeek, Day> days;
//
//    public Week() {}
//
//    public Week(Map<DaysOfWeek, Day> days, StudyRoom room){
//        this.room = room;
//        this.days = days;
//
//    }
//
//}
