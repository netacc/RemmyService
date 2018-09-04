package ru.remmy.entities;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="tasks")
public class TaskList extends ArrayList<Task>{

}