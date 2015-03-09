package in.websnoox.tappTablet.ui;

import in.websnoox.tappTablet.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomButton extends Button {
	private static final String HelveticaNeueLTProBd = "HelveticaNeueLTPro-Bd";
	private static final String HelveticaNeueLTProRoman = "HelveticaNeueLTPro-Roman";

	public CustomButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		if (!isInEditMode())
			init(attrs);

	}

	public CustomButton(Context context, AttributeSet attrs) {
		super(context, attrs);

		if (!isInEditMode())
			init(attrs);


	}

	public CustomButton(Context context) {
		super(context);

		if (!isInEditMode())
			init(null);

	}

	private void init(AttributeSet attrs) {
		// TODO Auto-generated method stub
		if (attrs != null) {
			TypedArray a = getContext().obtainStyledAttributes(attrs,
					R.styleable.CustomButton);
			String fontName = a
					.getString(R.styleable.CustomButton_custombuttonfont);
			if (HelveticaNeueLTProBd.equals(fontName)) {
				Typeface myTypeface = Typeface.createFromAsset(getContext()
						.getAssets(), "fonts/" + "HelveticaNeueLTPro-Bd.otf");
				setTypeface(myTypeface);
			}
			// if (fontName!=null) {
			// Typeface myTypeface =
			// Typeface.createFromAsset(getContext().getAssets(),
			// "fonts/"+fontName);
			// setTypeface(myTypeface);
			// }
			if (HelveticaNeueLTProRoman.equals(fontName)) {
				Typeface myTypeface = Typeface
						.createFromAsset(getContext().getAssets(), "fonts/"
								+ "HelveticaNeueLTPro-Roman.otf");
				setTypeface(myTypeface);
			}
			a.recycle();
		}

	}
}
