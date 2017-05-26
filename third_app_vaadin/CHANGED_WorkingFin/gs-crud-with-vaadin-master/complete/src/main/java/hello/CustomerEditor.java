package hello;

import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.themes.ValoTheme;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * A simple example to introduce building forms. As your real application is probably much
 * more complicated than this example, you could re-use this form in multiple places. This
 * example component is only used in VaadinUI.
 * <p>
 * In a real world application you'll most likely using a common super class for all your
 * forms - less code, better UX. See e.g. AbstractForm in Viritin
 * (https://vaadin.com/addon/viritin).
 */

@SpringComponent
@UIScope
public class CustomerEditor extends HorizontalLayout {

	private final CustomerRepository repository;

	/**
	 * The currently edited customer
	 */
	private Customer customer;

	/* Fields to edit properties in Customer entity */
	TextField firstName = new TextField("First name");
	TextField lastName = new TextField("Last name");
//	TextField winGames = new TextField("winGames");
//	TextField allGames = new TextField("allGames");
//	TextField commonScore = new TextField("commonScore");






	//final int guessedNumber = randomGeneretor();
	final int guessedNumber = 2894;


	/* Action buttons */
	Button save = new Button("", FontAwesome.SAVE);
	Button cancel = new Button("Cancel");
	Button delete = new Button("", FontAwesome.TRASH_O);

	// -------------------------------game -----------------------------------

	Button startNewGame = new Button("Start", FontAwesome.GAMEPAD);


	CssLayout actions = new CssLayout(save, cancel, delete,startNewGame);

	Binder<Customer> binder = new Binder<>(Customer.class);


	///-________________________________________________


	@Autowired
	public CustomerEditor(CustomerRepository repository) {
		this.repository = repository;

		//addComponents(firstName, lastName,finalScore, actions);
		firstName.setWidth(90, Unit.PIXELS);
		lastName.setWidth(105, Unit.PIXELS);
//		winGames.setWidth(60, Unit.PIXELS);
//		allGames.setWidth(60, Unit.PIXELS);
//		commonScore.setWidth(60, Unit.PIXELS);

		//addComponents(firstName, lastName,winGames,allGames,commonScore, actions);
		//addComponents(firstName, lastName,winGames,allGames,commonScore);
		addComponents(firstName, lastName);
		addComponent(actions);

		// bind using naming convention
		binder.bindInstanceFields(this);

		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

//***************************** No work  ***************
//		Window subWindow = new Window("Sub-window");
//		VerticalLayout subContent = new VerticalLayout();
//		subWindow.setContent(subContent);
//
//		// Put some components in it
//		subContent.addComponent(new Label("Meatball sub"));
//		subContent.addComponent(new Button("Awlright"));
//
//		// Center it in the browser window
//		subWindow.center();
//*****************************

		//******************************
		GridLayout grid2 = new GridLayout(2, 1000);

//		for (int i = 0; i < 3; i++) {
//			grid2.addComponent(new Button("Col " +
//					(grid2.getCursorX() + 1)));
//		}
//

//
		VerticalLayout gameLayout = new VerticalLayout();
		gameLayout.setWidth(1200, Unit.PIXELS);
		TextField  number = new TextField();
		Button putNumber =    new Button("Put");
		gameLayout.addComponents(number,putNumber, grid2);



//		grid2.addComponent(new Label("0000"), 0, 0);
//		grid2.addComponent(new Label("----"), 1,0);
//		grid2.addComponent(new Label("0"), 2, 0);



			putNumber.addClickListener( click -> {
//				grid2.addComponent(new Label(number.getValue()), 0, 14);
//				grid2.addComponent(new Label(reaction( number.getValue())), 1, 20);
				gameLayout.addComponent(new Label( reaction( number.getValue())));
				//repository.save(customer);
			});



		//putNumber.addClickListener( click -> gameLayout.addComponent(new Label(reaction( number.getValue()))));

		//putNumber.addClickListener( click -> grid2.addComponent(new Label("dedwdwdwd")));

//		startNewGame.addClickListener(e-> {
//			grid2.addComponent(new Label(number.getValue()), 0, iAdder);
//			grid2.addComponent(new Label(reaction( number.getValue())), 1, iAdder);
//
//		});

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> repository.save(customer));
		delete.addClickListener(e -> repository.delete(customer));
		cancel.addClickListener(e -> editCustomer(customer));
		startNewGame.addClickListener(e-> addComponents(gameLayout));




		//startNewGame.addClickListener(e-> addComponent(subWindow));
		// Open it in the UI

		setVisible(false);
	}

	public interface ChangeHandler {

		void onChange();
	}

	public final void editCustomer(Customer c) {
		if (c == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = c.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			customer = repository.findOne(c.getId());
		}
		else {
			customer = c;
		}
		cancel.setVisible(persisted);

		// Bind customer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(customer);

		setVisible(true);

		// A hack to ensure the whole form is visible
		save.focus();
		// Select all text in firstName field automatically
		firstName.selectAll();
	}


	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());

	}




	public String reaction ( String input){
		int a = 0, cows = 0, bulls = 0;
		int compA=0, compG =0;
		String s = "";

		if ( input.length()!=4) { return input + " - Wrong amount of digits";}

		try {
			a = Integer.parseInt(input);
		}
		catch (Exception exp ) { return input + " - Incorrect input ( not number)";}

		if (a == guessedNumber) {
			customer.setAllGames(String.valueOf(Integer.valueOf(customer.getAllGames())+1));
			customer.setWinGames(String.valueOf(Integer.valueOf(customer.getWinGames())+1));
			customer.setCommonScore(String.valueOf(Integer.valueOf(customer.getCommonScore())+100));
			//repository.save(customer);
			return input + " - Congratulations - you Won";
		}

		compA = a;
		compG = guessedNumber;

//		for (int i = 0; i < 4 ; i++) {
//			if (compA%10 == compG%10) bulls++;
//			compA/=10;
//			compG/=10;
//		}

		char [] guessedCh = String.valueOf(guessedNumber).toCharArray();
		char [] inpCh = input.toCharArray();



		for (int i = 0; i <4 ; i++) {
			for (int j = 0; j <4 ; j++) {
				if (inpCh[j] == guessedCh[i]){
					if(i==j) bulls++;
					else cows++;
				}
			}

		}

		if ((inpCh[0]==inpCh[1])||(inpCh[0]==inpCh[2])||(inpCh[0]==inpCh[3])||
				(inpCh[1]==inpCh[2])||(inpCh[1]==inpCh[3])||(inpCh[2]==inpCh[3])){
			s = input + " -  cows = " + cows + "  bulls = " + bulls +"   !input Digits are NOT uniq";
			return s;
		}

		s = input + " -  cows = " + cows + "  bulls = " + bulls;
		return s;
	}




	public static int randomGeneretor (){
		int res = 0;
		int [] a = new int [4];
		int sh;
		Random rand = new Random();
		a[0] = rand.nextInt(10);
		while ( true){
			sh = rand.nextInt(10);
			if ( a[0] != sh) {
				a[1] = sh;
				break;
			}
		}
		while ( true){
			sh = rand.nextInt(10);
			if (( a[0] != sh)&& (a[1] != sh)) {
				a[2] = sh;
				break;
			}
		}

		while ( true){
			sh = rand.nextInt(10);
			if (( a[0] != sh)&& (a[1] != sh)&& (a[2] != sh)) {
				a[3] = sh;
				break;
			}
		}

		res = a[0]+10*a[1] + 100*a[2] + 1000*a[3];

		return res;
	}


}
