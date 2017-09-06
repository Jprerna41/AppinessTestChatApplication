package com.example.pprasad.testchatapp;


import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class ListAdapter extends FirebaseListAdapter<Message> {

    private ChatActivity activity;

    public ListAdapter(ChatActivity activity, Class<Message> modelClass, int modelLayout, DatabaseReference ref) {
        super(activity, modelClass, modelLayout, ref);
        this.activity = activity;
    }

    @Override
    protected void populateView(View v, Message model, int position) {
        TextView messageText = (TextView) v.findViewById(R.id.message_text);
        TextView messageUser = (TextView) v.findViewById(R.id.message_user);
        TextView messageTime = (TextView) v.findViewById(R.id.message_time);

        messageText.setText(model.getMessageText());
        messageUser.setText(model.getMessageUser()+" ");
        String sTime =DateFormat.format("dd-MM-yyyy HH:mm:ss", model.getMessageTime()).toString();
        StringTokenizer tk = new StringTokenizer(sTime);
        String date = tk.nextToken();
        String time = tk.nextToken();

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm aa");
        Date dt;
        try {
            dt = sdf.parse(time);
            messageTime.setText("|" + " " + sdfs.format(dt));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Message chatMessage = getItem(position);
        if (chatMessage.getMessageUserId().equals(activity.getLoggedInUserName()))
            view = activity.getLayoutInflater().inflate(R.layout.message_received, viewGroup, false);
        else
            view = activity.getLayoutInflater().inflate(R.layout.message_sent, viewGroup, false);

        //generating view
        populateView(view, chatMessage, position);

        return view;
    }

    @Override
    public int getViewTypeCount() {
        // return the total number of view types. this value should never change
        // at runtime
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // return a value between 0 and (getViewTypeCount - 1)
        return position % 2;
    }
}

