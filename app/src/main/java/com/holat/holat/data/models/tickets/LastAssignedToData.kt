package com.holat.holat.data.models.tickets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LastAssignedToData(
    val active: Int, // 1
    val address: String?= null, // null
    val available: Int, // 1
    val avatar: String, // users/default.png
    val birthdate: String?= null, // null
    val city_id: String?= null, // null
    val country_id: String?= null, // null
    val created_at: String, // 2022-11-17 18:07:22
    val creator_id: Int, // 568
    val data_completed: Int, // 1
    val deleted_at: String?= null, // null
    val department_id: String?= null, // null
    val email: String, // 0033525@alrajhi.bank
    val email_verified_at: String, // 2022-11-17 15:07:22
    val entry_way_id: Int, // 1
    val fcm_token: String?= null, // null
    val gender: String?= null, // null
    val global_access: String, // organization
    val id: Int, // 607
    val image: String?= null, // null
    val is_verified: Int, // 0
    val login_token: String, // 9NHUIT6Xuq4oopdB9qNnhQeJ5nnuOQwcDfa4oMbu1rCL5O7Pmt
    val max_auto_assigned_tickets: String?= null, // null
    val mobile: String, // 966556243118
    val mobile_extension: String?= null, // null
    val name: String, // جمال  العتيبي
    val national_id: String, // 1063931735
    val national_type_id: Int, // 1
    val not_available_from: String?= null, // null
    val not_available_to: String?= null, // null
    val password_change_at: String, // 2024-01-30 15:12:55
    val portal: Int, // 0
    val position_id: String?= null, // null
    val reference_number: String?= null, // null
    val region_id: String?= null, // null
    val settings: String?= null, // null
    val show_fields: String, // ["[1,2,3]"]
    val super_admin: Int, // 0
    val updated_at: String, // 2024-01-30 15:12:55
    val verified_by_ldap: Int, // 0
    val verify_code: String?= null, // null
//    val view_user_roles_ids: List<Any>
) : Parcelable