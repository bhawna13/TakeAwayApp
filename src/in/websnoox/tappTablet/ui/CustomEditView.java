package in.websnoox.tappTablet.ui;

import in.websnoox.tappTablet.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomEditView extends EditText {
	private static final String HelveticaNeueLTProBd = "HelveticaNeueLTPro-Bd";
	private static final String HelveticaNeueLTProRoman = "HelveticaNeueLTPro-Roman";
	private static final String HelveticaNeueLTProBlk = "HelveticaNeueLTPro-Blk";
	private static final String HelveticaNeueLTProLt = "HelveticaNeueLTPro-Lt";

	public CustomEditView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if (!isInEditMode())
			init(attrs);

	}

	public CustomEditView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (!isInEditMode())
			init(attrs);

	}

	public CustomEditView(Context context) {
		super(context);
		if (!isInEditMode())
			init(null);
	}

	private void init(AttributeSet attrs) {
		// TODO Auto-generated method stub
		if (attrs != null) {
			TypedArray a = getContext().obtainStyledAttributes(attrs,
					R.styleable.CustomEditText);
			String fontName = a
					.getString(R.styleable.CustomEditText_customfontName);
			if (HelveticaNeueLTProBd.equals(fontName)) {
				Typeface myTypeface = Typeface.createFromAsset(getContext()
						.getAssets(), "fonts/" + "HelveticaNeueLTPro-Bd.otf");
				setTypeface(myTypeface);
			}
			if (HelveticaNeueLTProBlk.equals(fontName)) {
				Typeface myTypeface = Typeface.createFromAsset(getContext()
						.getAssets(), "fonts/" + "HelveticaNeueLTPro-Blk.otf");
				setTypeface(myTypeface);
			}
			if (HelveticaNeueLTProLt.equals(fontName)) {
				Typeface myTypeface = Typeface.createFromAsset(getContext()
						.getAssets(), "fonts/" + "HelveticaNeueLTPro-Lt.otf");
				setTypeface(myTypeface);
			}
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
