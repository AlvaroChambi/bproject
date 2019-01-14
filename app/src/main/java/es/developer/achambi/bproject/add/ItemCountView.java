package es.developer.achambi.bproject.add;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import es.developer.achambi.bproject.R;

public class ItemCountView extends ConstraintLayout implements View.OnClickListener {
    private TextView countText;
    public ItemCountView(Context context) {
        super(context);
        init( context );
    }

    public ItemCountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init( context );
    }

    public ItemCountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init( context );
    }

    private void init( Context context ) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.item_count_view, this);
        countText = findViewById(R.id.add_item_count_edit_text);
        findViewById(R.id.add_item_decrease_image).setOnClickListener(this);
        findViewById(R.id.add_item_increase_image).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if( view.getId() == R.id.add_item_decrease_image ) {
            int value = Integer.valueOf( countText.getText().toString() );
            if( value > 1 ) {
                countText.setText(String.valueOf(--value));
            }
        } else if( view.getId() == R.id.add_item_increase_image ) {
            int value = Integer.valueOf( countText.getText().toString() );
            countText.setText( String.valueOf( ++value ) );
        }
    }

    public int getCount() {
        return Integer.valueOf( countText.getText().toString() );
    }
}
