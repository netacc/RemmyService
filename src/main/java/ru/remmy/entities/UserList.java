package ru.remmy.entities;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="users")
public class UserList extends ArrayList<User> {

}
