//package hello;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.vaadin.data.Binder;
//import com.vaadin.event.ShortcutAction;
//import com.vaadin.server.FontAwesome;
//import com.vaadin.spring.annotation.SpringComponent;
//import com.vaadin.spring.annotation.UIScope;
//import com.vaadin.ui.Button;
//import com.vaadin.ui.CssLayout;
//import com.vaadin.ui.TextField;
//import com.vaadin.ui.VerticalLayout;
//import com.vaadin.ui.themes.ValoTheme;
//
///**
// * Created by Vladkuz on 24.05.2017.
// */
//public class Game extends VerticalLayout{
//
//
//    private String SinputNumber="";
//
//    TextField inputNumber = new TextField("inputNumber");
//    Button gameButton = new Button("gameButton", FontAwesome.ANGLE_DOUBLE_RIGHT);
//
//    CssLayout actions = new CssLayout(gameButton);
//
//    @Autowired
//    public Game() {
//        inputNumber.setWidth(90, Unit.PIXELS);
//
//        addComponents(inputNumber,actions);
//
//
//        // Configure and style components
//        setSpacing(true);
//        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
//        gameButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
//        gameButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
//
//        // wire action buttons to save, delete and reset
//
//
//        gameButton.addClickListener(e -> tryNumber(SinputNumber));
//        setVisible(false);
//    }
//
//    public interface ChangeHandler {
//
//        void onChange();
//    }
//
//    public final void  tryNumber(String c) {
//        if (c == null) {
//            setVisible(false);
//            return;
//        }
//
//        setVisible(true);
//
//    }
//    public void setChangeHandler(ChangeHandler h) {
//        // ChangeHandler is notified when either save or delete
//        // is clicked
//        gameButton.addClickListener(e -> h.onChange());
//
//    }
//}
