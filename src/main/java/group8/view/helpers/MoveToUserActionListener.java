package group8.view.helpers;

import group8.model.helpers.QuestionExchange;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoveToUserActionListener implements ActionListener {
    private JList<String> apiList;
    private DefaultListModel<String> apiListModel;
    private DefaultListModel<String> userListModel;
    private QuestionExchange questionExchange;

    public MoveToUserActionListener(JList<String> apiList, DefaultListModel<String> apiListModel,
                                    DefaultListModel<String> userListModel, QuestionExchange questionExchange) {
        this.apiList = apiList;
        this.apiListModel = apiListModel;
        this.userListModel = userListModel;
        this.questionExchange = questionExchange;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
