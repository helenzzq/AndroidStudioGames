package com.example.gamecenter.strategy.prompts;



import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.ViewGroup;
import android.widget.Button;

public interface Prompts {


     AlertDialog createPrompt(LayoutInflater inflater,
                              ViewGroup container,
                              Context context);

     Button getBackToMainBtn();

     Button getOnlyScoreBtn();

     Button getDisplayBothBtn();
}