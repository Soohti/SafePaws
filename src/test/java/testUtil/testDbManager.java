package testUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.AppState;
import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.PetMatchingAlgo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testDbManager {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
	
	@Test
	public void testInsertAccount() throws SQLException {
		Account account = new Account("CityuCS3334","CityuCS3334","M");
		DbManager.insertAccount(account);
		assertEquals("Account inserted successfully" + System.lineSeparator()
                    + "Member profile inserted successfully" + System.lineSeparator(), 
                    outContent.toString());
	}
	
	@Test
	public void testInsertPet() {
		Pet pet = new Pet();
		DbManager.insertPet(pet);
		assertEquals("Pet inserted successfully" + System.lineSeparator(), outContent.toString());
	}
	
	@Test
	public void testAuthenticateUser() throws SQLException {
		String username = "CityuCS3334";
		String password = "CityuCS3334";
		boolean flag = DbManager.authenticateUser(username, password);
		assertEquals(true,flag);
	}
	
	@Test
	public void testSelectAccount() {
		String username = "CityuCS3334";
		Account account = DbManager.selectAccount(username);
		Account expectedAccount = new Account("CityuCS3334","CityuCS3334","M");
		assertEquals(account, expectedAccount); //override @equals and @hashcode of account;
	}
	
	@Test
	public void testSelecPetById() {
		int id = 1;
		Pet pet = DbManager.selectPetById(id);
		Pet expectedPet = new Pet();
		assertEquals(pet,expectedPet);
	}
	
	
	@Test
	public void testInsertApplication() throws SQLException {
		String username = "CityuCS3334";
		Member account = (Member)DbManager.selectAccount(username);
		int id = 1;
		Pet pet = DbManager.selectPetById(id);
		AppState state = AppState.PENDING;
		Application application = new Application(account, pet, state);
		DbManager.insertApplication(application);
		assertEquals("Application inserted successfully" + System.lineSeparator(), outContent.toString());
	}
	
	@Test
	public void testViewAllApplications() throws SQLException {
		List<Application> applications = DbManager.viewAllApplication();
	}
	
	@Test
	public void testSelectApplication() {
		int Aid = 1;
		Application application = DbManager.selectApplication(Aid);

		String username = "CityuCS3334";
		Member account = (Member)DbManager.selectAccount(username);
		int Pid = 1;
		Pet pet = DbManager.selectPetById(Pid);
		AppState state = AppState.PENDING;
		Application expectedApplication = new Application(account, pet, state);
		assertEquals(application,expectedApplication);
	}
	
	@Test
	public 
}
