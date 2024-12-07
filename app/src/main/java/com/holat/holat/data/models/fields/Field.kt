package com.holat.holat.data.models.fields

data class Field(
    val constrain_field_id: Int, // 131
    val constrain_field_option: Int, // 5
    val constrain_field_option_ids: List<String>,
    val created_at: String, // 2021-07-05 15:57:14
    val deleted_at: Any?= null, // null
   val depend_on_fields: Any?=null, // ["ticket_type_id"]
    val details: String, // السبب الرئيسي
    val display_name_ar: String, // السبب الرئيسي     القطاع
    val display_name_en: String, // main reason
    val display_order: Int, // 6
    val `field`: String, // main_reason_id
    val form_field_type: FormFieldType?= null,
    val form_field_type_id: Int, // 1
    val form_id: Int, // 4
    val has_template: Int, // 0
    val icon_file: String, // /storage/agreement_icons/20240523140559954.jpg
    val id: Int, // 2
    val is_constrain_field: Int, // 1
    val is_migrated: Int, // 1
    val is_parent: Int, // 0
    val is_system_lookup: Int, // 1
    val list_preview: String, // select
    val new_lookup_id: Int, // 7
    val number_of_columns: Int, // 1
    val required: Int, // 0
    val required_if: Int, // 0
    val required_with_constrain: Int, // 0
    val show: Int, // 0
    val show_on_mapping_keys: Int, // 1
    val show_on_self_service: Int, // 1
    val system_field: Int, // 1
    val system_lookup_id: Int, // 8
    val template_file: String, // /storage/templates/20240514125992019.pdf
    val template_message_ar: String, // رساله القالب
    val template_message_en: String, // message template
    val updated_at: String, // 2024-06-25 12:57:31
    val validation_json: String // {"type":"text","max_length":null,"min_length":null,"number_of_rows":""}
)