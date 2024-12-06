package com.holat.holat.data.models.storeticket

data class StoreTicketResponse(
    val added_by_client: Int, // 1
    val address: Any, // null
    val already_escalated: Int, // 0
    val approval_workflow_applied_at: Any, // null
    val approval_workflow_id: Any, // null
    val approval_workflow_level_id: Any, // null
    val approval_workflow_status: Any, // null
    val auto_closed: Int, // 0
    val change_status_comment: Any, // null
    val city_id: Any, // null
    val client_hospital: ClientHospital,
    val client_hospital_id: Int, // 415323
    val client_id: Int, // 415270
    val client_side: Int, // 0
    val closed_at: Any, // null
    val closed_by: Any, // null
    val closed_by_client: Int, // 0
    val commercial_registration_number: Any, // null
    val company_name: Any, // null
    val contract_id: Any, // null
    val correct_delivery: Any, // null
    val country_id: Any, // null
    val created_at: String, // 2024-02-28 19:38:15
    val current_sla_transition_status: Any, // null
    val customer_creation_date: Any, // null
    val customer_replied: Int, // 0
    val data_completed: Any, // null
    val deleted_at: Any, // null
    val details: String, // شكوى للتجربة من فريق ساما تهتم بالبنك المركزي وسيتم حذفها بعد انتهاء التحربه
    val facility_file_number: Any, // null
    val feedback_link_expired_at: Any, // null
    val feedback_link_sent_at: Any, // null
    val files: List<Any>,
    val fine_details: Any, // null
    val fine_number: Any, // null
    val frozen_at: Any, // null
    val has_workflow_status: Int, // 1
    val hospital: Hospital,
    val hospital_id: Int, // 88
    val hours_count_from_approval: Any, // null
    val hours_count_from_creation: Int, // 0
    val id: Int, // 904796
    val insurance_number: Any, // null
    val is_child: Int, // 0
    val is_hidden: Int, // 0
    val is_parent: Int, // 0
    val last_action_updated_at: Any, // null
    val last_approved_by: Any, // null
    val last_approved_date: Any, // null
    val last_assigned_from: Any, // null
    val last_assigned_to_data: LastAssignedToData,
    val last_assigned_type: String, // role
    val last_assigned_user_at: Any, // null
    val last_status_updated_at: Any, // null
    val latitude: Any, // null
    val location_url: Any, // null
    val longitude: Any, // null
    val main_reason: MainReason,
    val main_reason_id: Int, // 2
    val main_reason_other: Any, // null
    val municipal_license_number: Any, // null
    val must_resolved_before: Any, // null
    val must_responded_before: String, // 2024-03-04 19:38:15
    val old_must_resolved_before: Any, // null
    val old_organization_id: Any, // null
    val parent_common_ticket: Any, // null
    val policy_container_number: Any, // null
    val port_owner_id: Any, // null
    val portal_request_number: Any, // null
    val priority: Priority,
    val priority_id: Int, // 3
    val quality_survey_added_by: Any, // null
    val quality_survey_weight: Any, // null
    val rate: Any, // null
    val readiness_creator_id: Any, // null
    val readiness_reason_comment: Any, // null
    val readiness_reason_id: Any, // null
    val region_id: Any, // null
    val requester_id: Int, // 415270
    val resolve_reminded_at: Any, // null
    val resolved_at: Any, // null
    val respond_reminded_at: Any, // null
    val responded_at: Any, // null
    val responsible_organization_id: Int, // 88
    val sector_reply: Any, // null
    val sequence_number: String, // 904796
    val shipping_agent: Any, // null
    val sla_id: Int, // 5
    val sla_status_id: Int, // 3
    val solution: Any, // null
    val solved_by: Any, // null
    val source: Source,
    val source_id: Int, // 4
    val special_number: Any, // null
    val status: Status,
    val status_id: Int, // 1
    val sub_reason: SubReason,
    val sub_reason_id: Int, // 29
    val sub_reason_other: Any, // null
    val sub_sub_reason_id: Int, // 69
    val sub_sub_sub_reason_id: Any, // null
    val ticket_created_at: Any, // null
    val ticket_number: Int, // 904796
    val ticket_reference_number: Any, // null
    val ticket_type: TicketType,
    val ticket_type_id: Int, // 1
    val updated_at: String, // 2024-02-28 19:38:16
    val user_id: Int, // 1
    val workflow_status_id: Int // 1
)