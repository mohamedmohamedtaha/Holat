package com.holat.holat.data.models.tickets

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataTicket(
    val added_by_client: Int, // 1
    val address: String?= null, // null
    val already_escalated: Int, // 0
    val approval_workflow_applied_at: String?= null, // null
    val approval_workflow_id: String?= null, // null
    val approval_workflow_level_id: String?= null, // null
    val approval_workflow_status: String?= null, // null
    val auto_closed: Int, // 0
    val change_status_comment: String?= null, // شكرا لكم
    val city_id: String?= null, // null
    val client_hospital: ClientHospital?= null,
    val client_hospital_id: Int?= null, // 415323
    val client_id: Int, // 415270
    val client_side: Int, // 0
    val closed_at: String?= null, // 2023-11-01 16:15:18
    val closed_by: String?= null, // null
    val closed_by_client: Int, // 1
    val commercial_registration_number: String?= null, // null
    val company_name: String?= null, // null
    val contract_id: String?= null, // null
    val correct_delivery: String?= null, // null
    val country_id: String?= null, // null
    val created_at: String, // 2023-10-29 11:26:06
    val current_sla_transition_status: String?=null, // passed
    val customer_creation_date: String?= null, // null
    val customer_replied: Int, // 1
    val data_completed: String?= null, // null
    val deleted_at: String?= null, // null
    val details: String?=null, // تم محاولة اختراق للبطاقة الائتمانية الخاصة بي لدا بنك الراجحي ولله الحمد كانت فارغة ولا يوجد بها المبلغ المراد سحبه وتم التواصل مع البنك لغلق البطاقة ولكن فوجئت ان البنك قد قام  بتعطيل بطاقة مدى وليس البطاقة الائتمانية وتوجيهي الى اصدار بدل بطاقة اخرى وتم خصم حوالي 34 ريال وقمت بتقديم شكوى للبنك يوم الخميس الموافق 26-10-2023  ولكن البنك لم يتواصل معي الى الان علماً بأن البطاقة الائتمانية مازالت تعمل ولم يتم ايقافها ومعرض للأحتيال مره اخرى.
    val facility_file_number: String?= null, // null
    val feedback_link_expired_at: String?= null, // null
    val feedback_link_sent_at: String?= null, // null
    val files: List<File>,
    val fine_details: String?= null, // null
    val fine_number: String?= null, // null
    val frozen_at: String?= null, // null
    val has_workflow_status: Int, // 1
    val hospital: Hospital,
    val hospital_id: Int?= null, // 9
    val hours_count_from_approval: String?= null, // null
    val hours_count_from_creation: Int, // 76
    val id: Long?=null, // 688414
    val insurance_number: String?= null, // null
    val is_child: Int, // 0
    val is_hidden: Int, // 0
    val is_parent: Int, // 0
    val last_action_updated_at: String?= null, // null
    val last_approved_by: String?= null, // null
    val last_approved_date: String?= null, // null
    val last_assigned_from: String?= null, // null
//    val last_assigned_to_data: List<LastAssignedToData>,
    val last_assigned_type: String, // user
    val last_assigned_user_at: String?= null, // 2023-10-29 11:37:39
    val last_status_updated_at: String?= null, // 2023-11-01 16:15:18
    val latitude: String?= null, // null
    val location_url: String?= null, // null
    val longitude: String?= null, // null
    val main_reason: MainReason,
    val main_reason_id: Int, // 1
    val main_reason_other: String?= null, // null
    val municipal_license_number: String?= null, // null
    val must_resolved_before: String?= null, // null
    val must_responded_before: String?=null, // 2023-11-03 11:26:06
    val old_must_resolved_before: String?= null, // null
    val old_organization_id: String?= null, // null
    val parent_common_ticket: String?= null, // null
    val policy_container_number: String?= null, // null
    val port_owner_id: String?= null, // null
    val portal_request_number: String?= null, // null
    val priority: Priority,
    val priority_id: Int, // 3
    val quality_survey_added_by: String?= null, // null
    val quality_survey_weight: String?= null, // null
    val rate: String?= null, // null
    val readiness_creator_id: String?= null, // null
    val readiness_reason_comment: String?= null, // null
    val readiness_reason_id: String?= null, // null
    val region_id: String?= null, // null
    val requester_id: Int, // 415270
    val resolve_reminded_at: String?= null, // null
    val resolved_at: String?= null, // 2023-11-01 15:52:34
    val respond_reminded_at: String?= null, // null
    val responded_at: String?= null, // null
    val responsible_organization_id: Int, // 9
    val sector_reply: String?= null, // عزيزنا العميل. إشارة إلى الشكوى المقدمة من قبلكم والمتضمنة اعتراضكم على رسوم بطاقة صراف نود الافادة انه بالرجوع الى النظام والتحقق نفيدكم انه تم اعادة الرسوم الى الحساب الجاري ومرفق لكم ذلك للتأكد  كما نفيدكم انه تم اشعاركم بالنتائج اعلاه وشكراً .
    val sequence_number: String, // 688414
    val shipping_agent: String?= null, // null
    val sla_id: Int?=null, // 5
    val sla_status_id: Int, // 3
    val solution: String?= null, // null
    val solved_by: Int?= null, // 2200
    val source: Source,
    val source_id: Int, // 4
    val special_number: String?= null, // null
    val status: Status?= null,
    val status_id: Int?=null, // 4
    val sub_reason: SubReason?= null,
    val sub_reason_id: Int, // 5
    val sub_reason_other: String?= null, // null
    val sub_sub_reason_id: Int, // 26
    val sub_sub_sub_reason_id: String?= null, // null
    val ticket_created_at: String?= null, // null
    val ticket_number: Int, // 688414
    val ticket_reference_number: String?= null, // null
    val ticket_type: TicketType,
    val ticket_type_id: Int, // 1
    val updated_at: String, // 2023-11-02 03:05:12
    val user_id: Int, // 1
    val workflow_status_id: Int?=null // 1
) : Parcelable {
    class DiffUtils: DiffUtil.ItemCallback<DataTicket>(){
        override fun areItemsTheSame(oldItem: DataTicket, newItem: DataTicket): Boolean {
            return oldItem.details == newItem.details
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: DataTicket, newItem: DataTicket): Boolean {
            return oldItem == newItem
        }

    }
}