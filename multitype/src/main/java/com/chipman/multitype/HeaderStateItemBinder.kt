package com.chipman.multitype

import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView

/**
 * Header ItemBinder
 *
 * @author Lowae
 */
abstract class HeaderStateItemBinder<VH : RecyclerView.ViewHolder> :
    ItemViewBaseDelegate<LoadState, VH>()