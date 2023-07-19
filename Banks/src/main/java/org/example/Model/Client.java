package org.example.Model;


import org.example.Utils.Sequence;

public class Client {
    private String name;
    private String firstName;
    private Long id;


    public Client(String name, String firstName) {
        setName(name);
        setFirstName(firstName);
        setId(Sequence.next());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
