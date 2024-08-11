# GUI Testing

## Request of Max (4) Categories At Once

![Multiple Category Request](../java/GUITestImages/multi_category_api.png)

## Ensuring All Four Categories Appear In Filter Options
![Multiple Category Request](../java/GUITestImages/multi_filter.png)

## Showing Each Category's Hard, Multiple Questions

<div style="display: flex; justify-content: space-between;">
    <img src="../java/GUITestImages/history_filter.png" style="width: 50%; margin-right: 5px;">
    <img src="../java/GUITestImages/history_results.png" style="width: 50%; margin-right: 5px;">
</div>

<div style="display: flex; justify-content: space-between;">
    <img src="../java/GUITestImages/pol_filter.png" style="width: 50%; margin-right: 5px;">
    <img src="../java/GUITestImages/pol_results.png" style="width: 50%; margin-right: 5px;">
</div>

<div style="display: flex; justify-content: space-between;">
    <img src="../java/GUITestImages/myth_filter.png" style="width: 50%; margin-right: 5px;">
    <img src="../java/GUITestImages/myth_results.png" style="width: 50%; margin-right: 5px;">
</div>

<div style="display: flex; justify-content: space-between;">
    <img src="../java/GUITestImages/science_filter.png" style="width: 50%; margin-right: 5px;">
    <img src="../java/GUITestImages/science_results.png" style="width: 50%; margin-right: 5px;">
</div>

## Moving Question From API -> User

<div style="display: flex; justify-content: space-between;">
    <img src="../java/GUITestImages/api_select_single_q.png" style="width: 50%; margin-right: 5px;">
    <img src="../java/GUITestImages/api_move_single_q.png" style="width: 50%; margin-right: 5px;">
</div>

## Sorting Multiple Questions in User Collection

### Ascending

<div style="display: flex; justify-content: space-between;">
    <img src="../java/GUITestImages/cat_asc.png" style="width: 35%; margin-right: 5px;">
    <img src="../java/GUITestImages/type_asc.png" style="width: 35%; margin-right: 5px;">
    <img src="../java/GUITestImages/diff_asc.png" style="width: 35%;">
</div>

### Descending

<div style="display: flex; justify-content: space-between;">
    <img src="../java/GUITestImages/cat_desc.png" style="width: 35%; margin-right: 5px;">
    <img src="../java/GUITestImages/type_desc.png" style="width: 35%; margin-right: 5px;">
    <img src="../java/GUITestImages/diff_desc.png" style="width: 35%;">
</div>

## Overwrite API Collection with one new category; Ensure User Collection Persists

<div style="display: flex; justify-content: space-between;">
    <img src="../java/GUITestImages/api_regen.png" style="width: 50%; margin-right: 5px;">
    <img src="../java/GUITestImages/api_regen_result.png" style="width: 50%; margin-right: 5px;">
</div>

## Moving over old category back into API collection; ensure filter updates dynamically

<div style="display: flex; justify-content: space-between;">
    <img src="../java/GUITestImages/old_q_back_api.png" style="width: 35%; margin-right: 5px;">
    <img src="../java/GUITestImages/old_q_back_api_action.png" style="width: 35%; margin-right: 5px;">
    <img src="../java/GUITestImages/old_q_back_api_result.png" style="width: 35%;">
</div>

## Saving Out Questions

<div style="display: flex; justify-content: space-between;">
    <img src="../java/GUITestImages/save_files.png" style="width: 50%; margin-right: 5px;">
    <img src="../java/GUITestImages/save_success.png" style="width: 50%; margin-right: 5px;">
</div>

## Loading In Same Questions (i.e. De-Dupe)

<div style="display: flex; justify-content: space-between;">
    <img src="../java/GUITestImages/load_dd_files.png" style="width: 50%; margin-right: 5px;">
    <img src="../java/GUITestImages/load_dd_success.png" style="width: 50%; margin-right: 5px;">
</div>

## Loading In Upon New Questions (i.e. Ensure Difficulty Sort is Maintained)

<div style="display: flex; justify-content: space-between;">
    <img src="../java/GUITestImages/load_ndd_file.png" style="width: 50%; margin-right: 5px;">
    <img src="../java/GUITestImages/load_ndd_success.png" style="width: 50%; margin-right: 5px;">
</div>

## Bugs Resulting from GUI Testing (Double Switch, Cancel Re-Prompting API Based on Last Successful API Request) - Have Since Been Resolved.

![Double Bug](../java/GUITestImages/double_switch.png)

<div style="display: flex; justify-content: space-between;">
    <img src="../java/GUITestImages/cancel_b1.png" style="width: 35%; margin-right: 5px;">
    <img src="../java/GUITestImages/cancel_b2.png" style="width: 35%; margin-right: 5px;">
    <img src="../java/GUITestImages/cancel_b3.png" style="width: 35%;">
</div>