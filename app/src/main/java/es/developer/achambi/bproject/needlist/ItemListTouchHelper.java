package es.developer.achambi.bproject.needlist;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import es.developer.achambi.bproject.R;
import es.developer.achambi.coreframework.ui.BaseSearchAdapter;
import es.developer.achambi.coreframework.utils.TouchHelper;

public class ItemListTouchHelper extends TouchHelper {
    ItemListTouchHelper(BaseSearchAdapter adapter) {
        super(adapter);
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder,
                                  int actionState) {
        if( viewHolder != null ) {
            getDefaultUIUtil().onSelected(viewHolder.itemView.findViewById(R.id.list_item_foreground));
        }
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder) {
        getDefaultUIUtil().clearView(viewHolder.itemView.findViewById(R.id.list_item_foreground));
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        getDefaultUIUtil().onDraw(c, recyclerView,
                viewHolder.itemView.findViewById(R.id.list_item_foreground),
                dX,dY, actionState, isCurrentlyActive );
    }
}
