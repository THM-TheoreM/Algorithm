import java.io.Serializable;
import java.util.List;
import java.util.LinkedList;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

class PersonEntity implements Serializable
{
    private int id;
    private String name;
    private int age;

    public void setId(int id) 
	{this.id = id;}

    public int getId() 
	{return id;}

    public void setName(String name) 
	{this.name = name;}

    public String getName() 
	{return name;}

    public void setAge(int age) 
	{this.age = age;}

    public int getAge() 
	{return age;}
}

interface PersonService extends Remote 
{public List<PersonEntity> GetList() throws RemoteException;}

class PersonServiceImpl extends UnicastRemoteObject implements PersonService
{

    public PersonServiceImpl() throws RemoteException
	{super();}

    public List<PersonEntity> GetList() throws RemoteException 
	{
        System.out.println("Get Person Start!");
        List<PersonEntity> personList=new LinkedList<PersonEntity>();
        
        PersonEntity person1=new PersonEntity();
        person1.setAge(25);
        person1.setId(0);
        person1.setName("Leslie");
        personList.add(person1);
        
        PersonEntity person2=new PersonEntity();
        person2.setAge(25);
        person2.setId(1);
        person2.setName("Rose");
        personList.add(person2);
        
        return personList;
    }
}

public class ProgramClient
{
	public static void main(String[] args) 
	{
        try
		{
			PersonService personService=(PersonService) Naming.lookup("rmi://127.0.0.1:6600/Program");
			List<PersonEntity> personList=personService.GetList();
			for(PersonEntity person: personList)
					System.out.println("ID: "+person.getId()+", Age: "+person.getAge()+", Name: "+person.getName());
		}catch(Exception ex){ex.printStackTrace();}
    }
}