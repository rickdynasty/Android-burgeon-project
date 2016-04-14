package com.tencent.tws.core.apidemos.widget.dialogfragment;

import android.content.DialogInterface;
import android.os.Bundle;

import com.tencent.tws.core.app.AlertDialog;
import com.tencent.tws.core.app.TwsDialog;
import com.tencent.tws.core.support.v4.app.DialogFragment;
import com.tencent.tws.burgeon.R;

public class MyDialogFragment extends DialogFragment {

	static MyDialogFragment newInstance(String title) {
		MyDialogFragment fragment = new MyDialogFragment();
		Bundle args = new Bundle();
		args.putString("title", title);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public TwsDialog onCreateDialog(Bundle savedInstanceState) {
		String title = getArguments().getString("title");
		return new AlertDialog.Builder(getActivity())
		.setIcon(R.drawable.ab_solid_holo_light)
		.setTitle(title)
		.setMessage(title)
		.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, 
					int whichButton) {
				
			}
		})
		.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, 
					int whichButton) {
				
			}
		}).create();
	}        

}