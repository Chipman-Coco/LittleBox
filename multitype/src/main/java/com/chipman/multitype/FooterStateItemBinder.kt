package com.chipman.multitype

import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView

/**
 * Footer ItemBinder
 *
 * @author Lowae
 */
abstract class FooterStateItemBinder<VH : RecyclerView.ViewHolder> :
    ItemViewBaseDelegate<LoadState, VH>()