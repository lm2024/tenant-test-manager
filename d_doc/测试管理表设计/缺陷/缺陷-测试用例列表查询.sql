缺陷
测试用例列表

请求网址
https://www.tapd.cn/api/entity/tcase/get_list
请求方法
POST


{"workspace_id":"60498179","entity_id":"1160498179001000009","entity_type":"bug","page":1,"sort_name":"","order":"","dsc_token":"B14PcWsEU6IsFDYQ"}



{
    "data": {
        "list": [],
        "count": "0",
        "show_fields": [
            "tcase_name",
            "test_plan_name",
            "executor",
            "executed_at",
            "result_status"
        ],
        "fields_info": {
            "tcase_name": {
                "html_type": "text",
                "label": "\u6d4b\u8bd5\u7528\u4f8b",
                "name": "tcase_name",
                "options": []
            },
            "test_plan_name": {
                "html_type": "text",
                "label": "\u6d4b\u8bd5\u8ba1\u5212",
                "name": "test_plan_name",
                "options": []
            },
            "executor": {
                "html_type": "text",
                "label": "\u6267\u884c\u4eba",
                "name": "executor",
                "options": []
            },
            "executed_at": {
                "html_type": "text",
                "label": "\u6267\u884c\u65f6\u95f4",
                "name": "executed_at",
                "options": []
            },
            "result_status": {
                "html_type": "text",
                "label": "\u6267\u884c\u7ed3\u679c",
                "name": "result_status",
                "options": []
            }
        },
        "view_conf_id": "0",
        "workspace_configs": {
            "workflow_infos": {
                "task": {
                    "status_map": {
                        "open": "\u672a\u5f00\u59cb",
                        "progressing": "\u8fdb\u884c\u4e2d",
                        "done": "\u5df2\u5b8c\u6210"
                    }
                }
            },
            "all_fields_map": {
                "60498179": {
                    "tcase": {
                        "priority": {
                            "editabled_type": "tselect",
                            "system_name": "priority",
                            "required": "",
                            "options": [
                                {
                                    "value": "\u9ad8",
                                    "label": "\u9ad8"
                                },
                                {
                                    "value": "\u4e2d",
                                    "label": "\u4e2d"
                                },
                                {
                                    "value": "\u4f4e",
                                    "label": "\u4f4e"
                                }
                            ]
                        }
                    }
                }
            },
            "user_permissions_map": {
                "60498179": [
                    "assistant::create",
                    "assistant::edit",
                    "assistant::save",
                    "roles::edit_feature_permission",
                    "roles::feature_index",
                    "roles::edit_feature",
                    "roles::delete_feature",
                    "botsetting::index",
                    "bugs::set_secret_bug",
                    "tasks::archive",
                    "bugs::archive",
                    "stories::archive",
                    "archive_list::index",
                    "archive_list::recover",
                    "workspaces::auto_scheduling",
                    "workspaces::delete_workspace_milestone",
                    "workspaces::export_workspace_milestones",
                    "workspaces::modify_workspace_milestone",
                    "workspaces::create_workspace_milestone",
                    "workspaces::get_workspace_milestone_list",
                    "workspaces::checklist_setting",
                    "roadmap::add_view",
                    "roadmap::edit_view",
                    "roadmap::delete_view",
                    "roadmap::add_snapshot",
                    "roadmap::edit_snapshot",
                    "roadmap::delete_snapshot",
                    "workspaces::modify_milestone_branch",
                    "workspaces::create_milestone_branch",
                    "bugworkflowsettings::getDefaultProperty",
                    "bugworkflowsettings::addworkflow",
                    "bugworkflowsettings::editworkflow",
                    "bugworkflowsettings::delete_workflow_cache",
                    "bugworkflowsettings::deleteWorkflow",
                    "bugworkflowsettings::workflowlist",
                    "bugworkflowsettings::set_default_workflow",
                    "bugworkflowsettings::steplist",
                    "bugworkflowsettings::edit_status",
                    "bugworkflowsettings::transition_setting",
                    "bugworkflowsettings::transition_config",
                    "bugworkflowsettings::view_workflow_chart",
                    "workspaces::bug_display_setting",
                    "bugsettings::copy_bug_settings",
                    "bugsettings::templatelist",
                    "bugsettings::set_default_template",
                    "bugsettings::set_bug_template_sort",
                    "bugsettings::bug_delete_template",
                    "bugsettings::bug_template_add",
                    "bugsettings::bug_template_edit",
                    "bugsettings::bug_more_field",
                    "bugsettings::bu_template_edit",
                    "bugsettings::bu_template_report",
                    "bugsettings::bu_template_fields",
                    "bugsettings::bu_template_apply",
                    "bugsettings::field_setting",
                    "system_field_configs::bug",
                    "bugsettings::customfieldsedit",
                    "bugsettings::delete_customfields_for_bug",
                    "bugsettings::customfieldslist",
                    "settings::ajax_edit",
                    "prong_setting::workflowlist",
                    "workflowsetting::story_workflowlist",
                    "prong_setting::view_workflow_chart",
                    "workflow::delete_story_status",
                    "prong_setting::story_template_list",
                    "prong_setting::story_template_add",
                    "prong_setting::story_delete_template",
                    "prong_setting::story_template_copy",
                    "prong_setting::story_template_edit",
                    "prongsetting::story_template_edit",
                    "prong_setting::set_story_template_sort",
                    "prong_setting::template_edit_from_workitem_type",
                    "prongsetting::template_edit_from_workitem_type",
                    "prongsetting::story_more_field",
                    "system_field_configs::story",
                    "custom_fields::story_add",
                    "prong_setting::story_field_setting",
                    "workitem_type::view",
                    "workitemtype::view",
                    "workitem_type::edit_workitem_type",
                    "workitemtype::edit_workitem_type",
                    "workitem_type::create",
                    "workitemtype::create",
                    "iterations::launch_form",
                    "launchform::iteration_add",
                    "team::calendar_setting",
                    "insight::create_indicator",
                    "insight::edit_indicator",
                    "insight::delete_indicator",
                    "story::story_transfer_bug",
                    "workspacereports::export",
                    "iterations::lock_iteration",
                    "insight::export",
                    "p4_source::config",
                    "p4_source::config_setting_intro",
                    "p4_source::config_use_intro",
                    "insight::delete",
                    "insight::delete_folder",
                    "insight::edit",
                    "insight::edit_folder",
                    "insight::create",
                    "insight::create_folder",
                    "imports::import_test_plan",
                    "gitlab_ci::edit",
                    "gitlab_ci::view",
                    "jenkins::index",
                    "pipeline::index",
                    "pipeline::detail",
                    "pipeline::ajax_get_history_table",
                    "pipeline::ajax_pipelines",
                    "jenkins::download_artifact",
                    "jenkins::start_build",
                    "pipeline::start_build",
                    "jenkins::edit",
                    "jenkins::view",
                    "jenkins::ajax_get_jenkins_list",
                    "jenkins::detail",
                    "jenkins::history",
                    "jenkins::ajax_get_build_object_table",
                    "jenkins::ajax_get_history_table",
                    "jenkins::ajax_get_nexus_data",
                    "jenkins::ajax_get_auto_test_data",
                    "jenkins::ajax_get_auto_test_page_data",
                    "pipeline::show_build_console_log",
                    "pipeline::show_task_console_log",
                    "pipeline::show_step_console_log",
                    "gantts::switch_dependency_conflict_resolution",
                    "test_plan::export",
                    "tcase_instance::execute_result_all",
                    "tcase_instance::execute_result",
                    "test_plan::ajax_get_testplan_last_modified",
                    "test_plan::save_plan_tcase",
                    "test_plan::save_plan_story",
                    "tcase_instance::delete_tcase_tset_plan_relation",
                    "tcase::add_save_from_plan_scope",
                    "test_plan::save_and_return",
                    "test_plan::copy",
                    "test_plan::delete",
                    "gantts::add_view",
                    "gantts::edit_view",
                    "gantts::delete_view",
                    "gantts::add_snapshot",
                    "gantts::edit_snapshot",
                    "gantts::delete_snapshot",
                    "kanban::create_view",
                    "kanban::edit_view",
                    "kanban::clone_view",
                    "kanban::delete_view",
                    "iterations::changestatus",
                    "iterations::planning",
                    "iterations::planning_story",
                    "iterations::export_iteration",
                    "exports::export_with_select_fields",
                    "exports::export_table",
                    "exports::export_xls",
                    "iterations::delete",
                    "iterations::edit",
                    "iteration::cgi_update_iteration",
                    "iterations::edit_iteration",
                    "iteration::ci_job_related",
                    "iteration::set_dashboard",
                    "iteration::ajax_set_dashboard_setting",
                    "iteration::ajax_get_job_with_related_status",
                    "iteration::do_ci_job_related",
                    "iterations::add",
                    "iterations::create_iteration",
                    "iterations::update_logo",
                    "iteration::cgi_create_iteration",
                    "iterations::add_iteration",
                    "stories::set_secret_story",
                    "gantts::display_setting",
                    "gantts::add_color_rule_config",
                    "gantts::delete_color_rule_config",
                    "gantts::update_color_rule_config",
                    "gantts::resort_color_rule_config",
                    "workspaces::workspace_setting_gantt",
                    "workspaces::gantt_display_setting",
                    "workspaces::gantt_date_display_setting",
                    "workspaces::gantt_color_rule",
                    "workspaces::gantt_color_rule_dialog",
                    "labels::index",
                    "label::edit",
                    "label::delete",
                    "wikis::share",
                    "attachments::wiki_delete",
                    "attachments::wiki_download",
                    "attachments::wiki_add",
                    "tcase::import_tcase",
                    "imports::import_tcase",
                    "tcase::export_tcase",
                    "exports::export_tcase_table",
                    "exports::export_tcase_with_select_fields",
                    "exports::export_tcase_xls",
                    "svnsource::config",
                    "svnsource::config_setting_intro",
                    "svnsource::config_use_intro",
                    "entitysort::set_sort",
                    "stories::delete_custom_statistic",
                    "bugreports::deletestat",
                    "stories::update_custom_statistic",
                    "stories::update_time_charts_statistic",
                    "bugreports::update_custom_statistic",
                    "bugreports::save_bugreport_sort",
                    "stories::add_custom_statistic",
                    "stories::add_time_charts_statistic",
                    "bugreports::add_custom_statistic",
                    "releases::export_table",
                    "releases::change_status",
                    "releases::planning_story",
                    "releases::edit",
                    "releases::add",
                    "gantts::add_highlight",
                    "gantts::edit_highlight",
                    "gantts::export",
                    "gantts::export_picture",
                    "auto_trigger::index",
                    "autotasksetting::index",
                    "autotasksetting::create_task",
                    "autotasksetting::set_status",
                    "recyclebin::index",
                    "recyclebin::recover",
                    "recyclebin::delete",
                    "recyclebin::stories_list",
                    "recyclebin::bugs_list",
                    "recyclebin::tasks_list",
                    "recyclebin::cards_list",
                    "recyclebin::tcases_list",
                    "recyclebin::get_filter_content",
                    "workspace::dashboard_settings",
                    "workspace::save_modules_setting",
                    "workspace::modules_display_setting",
                    "workspace::save_modules_direction_setting",
                    "markdown_wikis::download",
                    "wikis::download",
                    "card::copy_panel",
                    "board::edit_labels",
                    "taskboard::cgi_copy_card",
                    "card::copy_card",
                    "board::delete_custom_template",
                    "board::save_custom_template",
                    "tgitsource::config",
                    "tgitsource::config_setting_intro",
                    "tgitsource::config_use_intro",
                    "pipeline::fill_build_params",
                    "jenkinssetting::index",
                    "jenkinssetting::relate",
                    "jenkinssetting::do_relate",
                    "jenkinssetting::ajax_get_client_info",
                    "devopssetting::index",
                    "devopssetting::ajax_get_config_page",
                    "devopssetting::devops_setting_overview",
                    "devopssetting::ajax_save_config",
                    "devopssetting::config_done",
                    "gitlab_source::ajax_get_hook_info",
                    "github_source::ajax_get_hook_info",
                    "tgit_source::ajax_get_hook_info",
                    "gitlab_source::update_hook_url",
                    "github_source::update_hook_url",
                    "tgit_source::update_hook_url",
                    "gitlab_source::switch_secret_token",
                    "github_source::switch_secret_token",
                    "tgit_source::switch_secret_token",
                    "devopssetting::open_source",
                    "clouddevopssetting::index",
                    "clouddevopssetting::ajax_get_config_page",
                    "clouddevopssetting::devops_setting_overview",
                    "clouddevopssetting::ajax_save_config",
                    "clouddevopssetting::config_done",
                    "clouddevopssetting::open_source",
                    "settings::devops",
                    "githubsource::config",
                    "githubsource::config_setting_intro",
                    "githubsource::config_use_intro",
                    "githubsource::update_hook_url",
                    "githubsource::update_secret_token",
                    "githubsource::switch_secret_token",
                    "gitlabsource::config",
                    "gitlabsource::config_setting_intro",
                    "gitlabsource::config_use_intro",
                    "gitlabsource::update_hook_url",
                    "gitlabsource::update_secret_token",
                    "gitlabsource::switch_secret_token",
                    "board::copy",
                    "taskboard::cgi_copy_board",
                    "documents::transfer_mindmap_to_word",
                    "documents::transfer_mindmap_to_word_dialog",
                    "documents::update_mindmap_to_word",
                    "documents::show_first_generate_wiki",
                    "documents::show_save_as_wiki",
                    "documents::delete_file",
                    "documents::delete_folder",
                    "documents::cgi_delete_file",
                    "documents::download",
                    "documents::multi_download",
                    "documents::export_mindmap",
                    "documents::cgi_download_file",
                    "documents::edit",
                    "documents::rename",
                    "documents::upload",
                    "documents::create_folder",
                    "documents::update_file",
                    "documents::add_word",
                    "documents::add_mindmap",
                    "documents::inline_update_name",
                    "documents::move_frame",
                    "documents::cgi_create_folder",
                    "documents::cgi_upload_image",
                    "documents::cgi_upload_image_h5",
                    "documents::save_mindmap_position",
                    "documents::transfer_file_to_word",
                    "documents::release_document_folders",
                    "documents::release_to_workspace",
                    "documents::copy",
                    "documents::cgi_rename_document",
                    "documents::cgi_move_document",
                    "documents::cgi_copy_document",
                    "documents::cgi_create_word",
                    "documents::cgi_create_mindmap",
                    "documents::view",
                    "documents::file_list",
                    "documents::show",
                    "documents::cgi_get_file_list",
                    "documents::get_mindmap_image",
                    "documents::cgi_get_comments",
                    "documents::cgi_add_comment",
                    "documents::cgi_get_word_content",
                    "documents::transfer_xmind_to_mindmap",
                    "documents::add_comment",
                    "mindmap::delete",
                    "mindmap::move_to_folder",
                    "mindmap::is_first_transform_wiki",
                    "mindmap::show_first_generate_wiki",
                    "mindmap::show_save_as_wiki",
                    "mindmap::export",
                    "mindmap::show_export_type",
                    "mindmap::add",
                    "mindmap::inline_edit_name",
                    "mindmap::edit",
                    "mindmap::index",
                    "mindmap::view",
                    "mindmap::show_folder_tree",
                    "card::relate_from_mindmap_dialog",
                    "qareports::qatemplatelist",
                    "qareports::qatemplateedit",
                    "qareports::qatemplateeditcontent",
                    "qareports::qatemplatedelete",
                    "qareports::qatemplatepreview",
                    "qareports::qatemplateddel",
                    "qareports::qatemplatedetaileditview",
                    "qareports::ajax_check_qatemplate_title",
                    "qareports::edit_rich_editor",
                    "qareports::edit_workitem_view",
                    "qareports::edit_workitem_stat",
                    "workspaces::report_display_setting",
                    "qareports::qatemplateadd",
                    "card::delete_attachment",
                    "taskboard::cgi_delete_attachment",
                    "taskboard::cgi_delete_relate",
                    "card::download_attachment",
                    "attachments::card_download",
                    "taskboard::cgi_get_attachment_detail",
                    "taskboard::cgi_get_attachment_content",
                    "taskboard::cgi_get_word_content_attachment",
                    "taskboard::cgi_get_text_content_attachment",
                    "taskboard::cgi_download_attachment",
                    "attachments::card_add",
                    "card::relate_document",
                    "taskboard::cgi_add_attachment",
                    "taskboard::cgi_relate_document",
                    "card::export",
                    "board::export",
                    "card::delete",
                    "taskboard::cgi_delete_card",
                    "card::edit_card",
                    "card::sort_card",
                    "taskboard::cgi_update_card_field",
                    "taskboard::cgi_add_checklist",
                    "taskboard::cgi_change_checklist_status",
                    "card::add_checklist",
                    "card::checklist_done",
                    "card::checklist_start",
                    "card::move_checklist",
                    "card::cancel_cover",
                    "card::set_cover",
                    "card::finish_card",
                    "card::re_open_card",
                    "card::add_card",
                    "taskboard::cgi_add_card",
                    "board::delete_column",
                    "taskboard::cgi_delete_column",
                    "columns::edit",
                    "board::sort_column",
                    "taskboard::cgi_update_column_name",
                    "board::save_column_name",
                    "taskboard::cgi_add_column",
                    "board::delete",
                    "board::delete_board",
                    "taskboard::cgi_delete_board",
                    "board::edit",
                    "board::update_board_name",
                    "board::change_status",
                    "taskboard::cgi_update_board_name",
                    "board::add",
                    "taskboard::cgi_add_board",
                    "board::submit_create_by_template",
                    "workspacereports::delete",
                    "workspacereports::copy",
                    "workspacereports::edit",
                    "workspacereports::add",
                    "workspacereports::create_workspace_report_test",
                    "fileapp::delete_file",
                    "fileapp::delete_folder",
                    "fileapp::multi_delete",
                    "fileapp::upload",
                    "fileapp::rename",
                    "fileapp::update_file",
                    "fileapp::create_folder",
                    "fileapp::save_desc",
                    "fileapp::move_frame",
                    "fileapp::move",
                    "fileapp::show",
                    "fileapp::file_list",
                    "fileapp::download",
                    "fileapp::multi_download",
                    "fileapp::preview",
                    "comment::quick_add_comment",
                    "comment::add_file_comment",
                    "comment::download_attachment",
                    "comment::upload_attachment",
                    "comment::load_more_comment",
                    "comment::delete_comment",
                    "fileapp::star_folder_all",
                    "fileapp::star_file",
                    "fileapp::preview_office_type",
                    "fileapp::image_preview_office_type",
                    "fileapp::preview_wps_type",
                    "fileapp::send_mail",
                    "fileapp::multi_share",
                    "fileapp::try_share",
                    "fileapp::check_name_duplicated",
                    "fileapp::card_file_association_thumb",
                    "card::relate_from_file_dialog",
                    "wikis::delete",
                    "wikis::edit",
                    "wikis::add",
                    "wikis::check_titile_exist",
                    "markdown_wikis::edit",
                    "markdown_wikis::add",
                    "markdown_wikis::check_titile_exist",
                    "markdownwikis::check_titile_exist",
                    "wikis::view",
                    "card::relate_from_wiki_dialog",
                    "wiki::cgi_get_items",
                    "wiki::cgi_get_item",
                    "wiki::cgi_get_tags",
                    "wikis::history",
                    "wikis::get_wiki",
                    "wikis::add_comment",
                    "wikis::add_like",
                    "markdownwikis::view",
                    "markdown_wikis::history",
                    "markdownwikis::get_wiki",
                    "markdown_wikis::add_comment",
                    "markdown_wikis::add_like",
                    "markdownwikis::history",
                    "markdownwikis::add_comment",
                    "markdownwikis::add_like",
                    "workspaces::workspace_setting_release",
                    "releases::progress_setting",
                    "workspaces::ce_setting",
                    "workspaces::nce_setting",
                    "workspaces::workspace_setting_testplan",
                    "testplancustomsetting",
                    "testplantemplate",
                    "testplantemplate::test_plan_more_field",
                    "testplan::test_plan_field_setting",
                    "workspaces::testplan_display_setting",
                    "test_plan_template::test_plan_template_add",
                    "system_field_configs::test_plan_type",
                    "test_plan_template::test_plan_template_list",
                    "test_plan_custom_setting::custom_field_edit",
                    "workspacereporttemplates::preview",
                    "workspacereporttemplates::index",
                    "workspacereporttemplates::add",
                    "workspacereporttemplates::edit",
                    "workspacereporttemplates::delete",
                    "bugsettings::qatemplatelist",
                    "workspaces::workspace_setting_tcase",
                    "tcasecustomsetting",
                    "tcasetemplate",
                    "tcasetemplate::tcase_more_field",
                    "tcase::tcase_field_setting",
                    "workspaces::tcase_display_setting",
                    "tcase_template::tcase_template_add",
                    "system_field_configs::tcase_type",
                    "system_field_configs::tcase_priority",
                    "tcase_template::tcase_template_list",
                    "settings::discuss_setting",
                    "workspaces::workspace_function_enable",
                    "workspaces::ajax_save_workspace_metrology",
                    "workspaces::ajax_save_optional_func_sw",
                    "workspaces::fake_workspace_edit",
                    "workspaces::workspace_inline_edit",
                    "workspaces::replace_logo_from_tmp",
                    "workspaces::upload_logo_ajax",
                    "project::cgi_set_project_name",
                    "project::cgi_set_projetc_logo",
                    "settings::security_ip",
                    "menusettings",
                    "menu_settings::config_navbar",
                    "bugs::get_workspace_tree_for_transfer_bug",
                    "bugs::show_workspace_tree_for_transfer_bug",
                    "tobjects::builder",
                    "workflow::chart_preview",
                    "iterations::stories_batch_process_new",
                    "iterations::stories_batch_process_status",
                    "stories::get_story_status_transfer_info_for_batch",
                    "attachments::task_delete",
                    "attachments::task_download",
                    "attachments::task_add",
                    "tasks::changestatus",
                    "tasks::delete",
                    "tasks::export_task",
                    "imports::import_task",
                    "imports::import_preview_task",
                    "tasks::edit",
                    "tasks::tasks_batch_edit",
                    "tracking::timeedit_task",
                    "tasks::inline_update",
                    "tasks::inline_update_intab",
                    "tasks::add",
                    "iterations::to_test",
                    "workspacereports::to_test",
                    "iterations::assignment",
                    "iterations::save_assignment_workitems_change",
                    "iterations::do_planning",
                    "iterations::inline_update",
                    "bugs::move_bug",
                    "bugs::move_bugs",
                    "bugs::replicate",
                    "bugs::copy_bug",
                    "bugs::submit_from_copy",
                    "bugs::edit",
                    "bugs::batch_edit",
                    "bugs::submit_from_edit",
                    "bugs::inline_update",
                    "bugs::inline_update_intab",
                    "bugs::add",
                    "bugs::add_from_tobject",
                    "bugs::submit_from_add",
                    "bugs::quickly_add_bug",
                    "bugs::add_from_tcase_result",
                    "bugs::addce",
                    "bugs::add_support",
                    "bugs::quickly_add_bug_from_story",
                    "bugs::add_from_jenkins_autotest",
                    "attachments::bug_delete",
                    "attachments::bug_download",
                    "attachments::bug_add",
                    "attachments::story_delete",
                    "attachments::story_download",
                    "attachments::story_add",
                    "stories::edit",
                    "stories::inline_update",
                    "stories::change_parent_story",
                    "stories::changePriority",
                    "stories::ajax_batch_edit_stories_parent",
                    "stories::update_story_category",
                    "stories::add",
                    "stories::quickly_add_story",
                    "stories::add_from_mgr",
                    "stories::addce",
                    "stories::add_support",
                    "stories::quick_add_child_story",
                    "stories::quick_add_story",
                    "stories::edit_draft",
                    "stories::remove_draft",
                    "stroies::worktable_remove_draft",
                    "stories::quickly_add_story_from_tab",
                    "stories::transfer_mindmap_to_story",
                    "storywalls::add_card",
                    "categories::index",
                    "categories::view",
                    "categories::add",
                    "categories::edit",
                    "categories::delete",
                    "stories::move_story",
                    "stories::move_story_in_view_page",
                    "stories::move_story_view_tree",
                    "stories::export_story",
                    "imports::import_story",
                    "imports::import_preview_story",
                    "stories::copy_story",
                    "stories::delete",
                    "stories::batch_delete",
                    "stories::secrecy",
                    "workspaces::workspace_setting_launch_view",
                    "prong_setting::custom_field_setting",
                    "launchsetting::index",
                    "launchsetting::templatelist",
                    "launchsetting::templatedelete",
                    "launchsetting::templateedit",
                    "launchsetting::edit_activity",
                    "launchsetting::activity_list",
                    "launchsetting::select_factors",
                    "launchsetting::applytemplate",
                    "launchsetting::list_factors",
                    "launchsetting::edit_factor",
                    "launchsetting::set_default_template",
                    "launchsetting::set_template_sort",
                    "launchsetting::load_activity_factors",
                    "prong_setting::launch_field_setting",
                    "prong_setting::launch_field_edit",
                    "launchsetting::copy_launch_template",
                    "userviews::workspace_view_index",
                    "userviews::workspace_view_list",
                    "userviews::workspace_view_edit",
                    "userviews::delete_workspace_view",
                    "userviews::update_workspace_view_settings",
                    "userviews::update_view_settings",
                    "workspaces::workspace_setting_bug",
                    "bugworkflowsettings::status_fields_setting",
                    "bugworkflowsettings::bug_workflow_select_fields",
                    "bug_workflow_settings::status_edit_fields_setting",
                    "workspaces::bug_switch_setting",
                    "bugworkflowsettings::status_edit_fields_setting",
                    "workflows::bugtrace_workflow_history",
                    "workspaces::ajax_save_bug_setting_sw",
                    "workspaces::workspace_setting_iteration",
                    "prong_setting::copy_iteration_setting",
                    "prong_setting::iteration_status_config",
                    "prong_setting::iteration_template_list",
                    "progress_setting::iteration_progress",
                    "prong_setting::set_iteration_template_sort",
                    "prong_setting::iteration_progress",
                    "prong_setting::iteration_template_edit",
                    "prongsetting::iteration_more_field",
                    "iterations::ajax_enable_extend_iteration_name",
                    "prong_setting::iteration_field_setting",
                    "workspaces::iteration_display_setting",
                    "workspaces::iteration_switch_setting",
                    "system_field_configs::iteration_status",
                    "prong_setting::iteration_template_add",
                    "prongsetting::iteration_delete_template",
                    "prongsetting::save_iteration_progress",
                    "prong_setting::iteration_workitem_type_edit",
                    "prongsetting::iteration_workitem_type_edit",
                    "workspaces::delete_iteration_workitem_type",
                    "workspaces::workspace_setting_tasks",
                    "prong_setting::custom_task_field",
                    "prong_setting::task_field_setting",
                    "workspaces::workspace_setting_story",
                    "prong_setting::story_source_config",
                    "sourcefiles::sourcefile_type_config",
                    "prong_setting::copy_story_setting",
                    "workspaces::story_display_setting",
                    "prong_setting::status_edit_fields_setting",
                    "workspaces::story_switch_setting",
                    "prongsetting::template_add_from_workitem_type",
                    "workspaces::ajax_save_story_setting_sw",
                    "workflows::config_edit_page_show_fields_submit",
                    "workflows::config_show_fields_submit",
                    "workspaces::workspace_secret_user_group_setting",
                    "workspaces::workspace_setting_project",
                    "workspaces::workspace_settings_module",
                    "modules::index",
                    "features::index",
                    "features::add",
                    "features::edit",
                    "features::delete",
                    "versions::index",
                    "versions::add",
                    "versions::edit",
                    "versions::operate",
                    "baselines::index",
                    "baselines::add",
                    "baselines::edit",
                    "baselines::operate",
                    "workspacesettings::common_params_setting",
                    "workspace_settings::common_params_setting",
                    "modules::ajax_set_sort",
                    "modules::edit",
                    "modules::do_copy_module_setting",
                    "baselines::edit_submit",
                    "workspaces::message_setting",
                    "workspaces::save_message_setting",
                    "workspaces::save_message_other_setting",
                    "workspaces::delete_message_setting",
                    "roles::edit_permission",
                    "roles::index",
                    "roles::edit",
                    "roles::delete",
                    "settings::add_member_from_permission",
                    "settings::add_member_to_permission_group",
                    "settings::team",
                    "settings::team_nav",
                    "settings::team_batch_delete",
                    "settings::team_save_changes",
                    "settings::_add_member",
                    "settings::edit_member",
                    "settings::delete_member",
                    "workspaces::copy_members",
                    "settings::inline_update",
                    "settings::cloud_edit_user_info",
                    "settings::add_company_member",
                    "settings::cloud_delete_project_member",
                    "project::cgi_add_project_user",
                    "project::cgi_remove_user_from_project",
                    "personal_settings::edit_member",
                    "personalsettings::edit_member",
                    "settings::import_members",
                    "bugreports::export_setting",
                    "buglists::export",
                    "bugreports::batchprocess",
                    "bugreports::get_bug_status_transfer_info_for_batch",
                    "bugreports::batch_change_bug_status",
                    "imports::import_bug",
                    "imports::import_preview_bug",
                    "bugs::delete",
                    "bugs::merge",
                    "bugs::bug_merge_validate",
                    "bu_admin",
                    "workspace_admin",
                    "workspace_setting",
                    "workspace_manager",
                    "mini_project_manager",
                    "mini_project_admin",
                    "mini_project_setting"
                ]
            },
            "disabled_permission_map": {
                "60498179": {
                    "tcase": {
                        "enable_filter_parent_dialog": ""
                    }
                }
            },
            "enable_map": {
                "60498179": {
                    "enabled_task": "false",
                    "enabled_board": "false",
                    "enabled_story": "true",
                    "enabled_bug": "true",
                    "enabled_measurement": "true",
                    "enabled_bug_measurement": "true",
                    "enable_to_test": "false",
                    "accessible_menu_launchforms": "true",
                    "accessible_func_to_test": "true",
                    "enable_testplan": "true",
                    "enable_launchform": "true",
                    "enabled_story_category": "true",
                    "enabled_report": "true",
                    "enabled_quick_create": "true",
                    "enable_parent_story_edit_iteration": "false",
                    "enable_timesheet": "false",
                    "sync_task_iteration_with_story": "true",
                    "enable_feature": "false",
                    "enable_secret_story": "true",
                    "enable_secret_bug": "false",
                    "enabled_tcase": "true",
                    "enabled_program": "false",
                    "enable_multi_linkage_fields": "false"
                }
            },
            "all_edit_field_rules_map": [],
            "custom_copy_link_configs": {
                "60498179": []
            },
            "system_copy_link_display_configs": {
                "60498179": [
                    {
                        "name": "\u590d\u5236\u6807\u9898\u548c\u94fe\u63a5",
                        "code": "titleAndLinkCopy",
                        "display": "1",
                        "copyType": "sys"
                    },
                    {
                        "name": "\u590d\u5236\u6807\u9898\u548c\u5185\u7f51\u94fe\u63a5",
                        "code": "convertUrl",
                        "display": "1",
                        "copyType": "sys"
                    },
                    {
                        "name": "\u590d\u5236\u6807\u9898\u548c\u9879\u76ee\u96c6\u94fe\u63a5",
                        "code": "titleAndLinkCopyProgram",
                        "display": "1",
                        "copyType": "sys"
                    },
                    {
                        "name": "\u590d\u5236 ID",
                        "code": "idCopy",
                        "display": "1",
                        "copyType": "sys"
                    },
                    {
                        "name": "\u590d\u5236\u6e90\u7801\u5173\u952e\u5b57",
                        "code": "commitKeyword",
                        "display": "1",
                        "copyType": "sys"
                    }
                ]
            },
            "workspace_plan_lock_info": {
                "60498179": {
                    "iteration": []
                }
            },
            "workspace_plan_apps": {
                "60498179": []
            },
            "workspace_plan_permission_map": {
                "60498179": {
                    "iteration": [
                        "iterations::add",
                        "iteration::cgi_create_iteration",
                        "iterations::create_iteration",
                        "iterations::update_logo",
                        "iterations::add_iteration",
                        "iterations::edit",
                        "iteration::cgi_update_iteration",
                        "iterations::edit_iteration",
                        "iteration::ci_job_related",
                        "iteration::set_dashboard",
                        "iteration::ajax_set_dashboard_setting",
                        "iteration::ajax_get_job_with_related_status",
                        "iteration::do_ci_job_related",
                        "iterations::inline_update",
                        "iterations::delete",
                        "iterations::export_iteration",
                        "exports::export_with_select_fields",
                        "exports::export_table",
                        "exports::export_xls",
                        "iterations::planning",
                        "iterations::planning_story",
                        "iterations::do_planning",
                        "iterations::assignment",
                        "iterations::save_assignment_workitems_change",
                        "iterations::to_test",
                        "workspacereports::to_test",
                        "iterations::launch_form",
                        "launchform::iteration_add",
                        "iterations::changestatus"
                    ]
                }
            },
            "workspace_effort_unit_map": {
                "60498179": "day"
            },
            "workspace_holiday_map": [
                {
                    "2012": [
                        {
                            "9": [
                                "29"
                            ],
                            "1": [
                                "21",
                                "29"
                            ]
                        },
                        {
                            "10": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05",
                                "06",
                                "07"
                            ],
                            "9": [
                                "30"
                            ],
                            "1": [
                                "01",
                                "02",
                                "03",
                                "22",
                                "23",
                                "24",
                                "25",
                                "26",
                                "27",
                                "28"
                            ]
                        }
                    ],
                    "2011": [
                        {
                            "12": [
                                "31"
                            ]
                        },
                        []
                    ],
                    "2013": [
                        {
                            "1": [
                                "05",
                                "06"
                            ],
                            "2": [
                                "17"
                            ],
                            "4": [
                                "07",
                                "27",
                                "28"
                            ],
                            "6": [
                                "08",
                                "09"
                            ],
                            "9": [
                                "22",
                                "29"
                            ],
                            "10": [
                                "12"
                            ]
                        },
                        {
                            "1": [
                                "01",
                                "02",
                                "03"
                            ],
                            "2": [
                                "09",
                                "10",
                                "11",
                                "12",
                                "14",
                                "15",
                                "13",
                                "08"
                            ],
                            "4": [
                                "04",
                                "05",
                                "06",
                                "29",
                                "30"
                            ],
                            "5": [
                                "01"
                            ],
                            "6": [
                                "10",
                                "11",
                                "12"
                            ],
                            "9": [
                                "19",
                                "20",
                                "21"
                            ],
                            "10": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05",
                                "06",
                                "07"
                            ]
                        }
                    ],
                    "2014": [
                        {
                            "1": [
                                "26"
                            ],
                            "2": [
                                "08"
                            ],
                            "5": [
                                "04"
                            ],
                            "9": [
                                "28"
                            ],
                            "10": [
                                "11"
                            ]
                        },
                        {
                            "1": [
                                "01",
                                "31"
                            ],
                            "2": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05",
                                "06"
                            ],
                            "4": [
                                "05",
                                "07"
                            ],
                            "5": [
                                "01",
                                "02"
                            ],
                            "6": [
                                "02"
                            ],
                            "9": [
                                "08"
                            ],
                            "10": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05",
                                "06",
                                "07"
                            ]
                        }
                    ],
                    "2015": [
                        {
                            "1": [
                                "04"
                            ],
                            "2": [
                                "15",
                                "28"
                            ],
                            "10": [
                                "10"
                            ],
                            "9": [
                                "06"
                            ]
                        },
                        {
                            "1": [
                                "01",
                                "02",
                                "03"
                            ],
                            "2": [
                                "17",
                                "18",
                                "19",
                                "20",
                                "23",
                                "24",
                                "25"
                            ],
                            "4": [
                                "06"
                            ],
                            "5": [
                                "01"
                            ],
                            "6": [
                                "22"
                            ],
                            "10": [
                                "01",
                                "02",
                                "05",
                                "06",
                                "07"
                            ],
                            "9": [
                                "03",
                                "04",
                                "05"
                            ]
                        }
                    ],
                    "2016": [
                        {
                            "6": [
                                "12"
                            ],
                            "9": [
                                "18"
                            ],
                            "10": [
                                "08",
                                "09"
                            ]
                        },
                        {
                            "1": [
                                "01"
                            ],
                            "2": [
                                "08",
                                "09",
                                "10",
                                "11",
                                "12"
                            ],
                            "4": [
                                "04"
                            ],
                            "5": [
                                "02"
                            ],
                            "6": [
                                "09",
                                "10"
                            ],
                            "9": [
                                "15",
                                "16"
                            ],
                            "10": [
                                "03",
                                "04",
                                "05",
                                "06",
                                "07"
                            ]
                        }
                    ],
                    "2017": [
                        {
                            "1": [
                                "22"
                            ],
                            "2": [
                                "04",
                                "05"
                            ],
                            "4": [
                                "01"
                            ],
                            "5": [
                                "27"
                            ],
                            "9": [
                                "30"
                            ]
                        },
                        {
                            "1": [
                                "01",
                                "02",
                                "27",
                                "28",
                                "29",
                                "30",
                                "31",
                                "25",
                                "26"
                            ],
                            "2": [
                                "01",
                                "02",
                                "03"
                            ],
                            "4": [
                                "02",
                                "03",
                                "04"
                            ],
                            "5": [
                                "01",
                                "28",
                                "29",
                                "30"
                            ],
                            "10": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05",
                                "06",
                                "07",
                                "08"
                            ]
                        }
                    ],
                    "2018": [
                        {
                            "2": [
                                "11",
                                "12",
                                "24"
                            ],
                            "4": [
                                "08",
                                "28"
                            ],
                            "9": [
                                "29",
                                "30"
                            ],
                            "12": [
                                "29"
                            ]
                        },
                        {
                            "1": [
                                "01"
                            ],
                            "2": [
                                "14",
                                "15",
                                "16",
                                "17",
                                "19",
                                "21",
                                "22",
                                "18",
                                "20"
                            ],
                            "4": [
                                "05",
                                "06",
                                "07",
                                "29",
                                "30"
                            ],
                            "5": [
                                "01"
                            ],
                            "10": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05",
                                "06",
                                "07"
                            ],
                            "6": [
                                "18"
                            ],
                            "9": [
                                "24"
                            ],
                            "12": [
                                "31"
                            ]
                        }
                    ],
                    "1970": [
                        {
                            "1": [
                                "00"
                            ]
                        },
                        []
                    ],
                    "2019": [
                        {
                            "2": [
                                "02"
                            ],
                            "9": [
                                "29"
                            ],
                            "10": [
                                "12"
                            ]
                        },
                        {
                            "1": [
                                "01"
                            ],
                            "2": [
                                "04",
                                "05",
                                "06",
                                "07",
                                "08",
                                "09",
                                "10",
                                "03",
                                "11"
                            ],
                            "4": [
                                "05",
                                "06",
                                "07"
                            ],
                            "5": [
                                "01"
                            ],
                            "6": [
                                "07",
                                "08",
                                "09"
                            ],
                            "9": [
                                "13",
                                "14",
                                "15"
                            ],
                            "10": [
                                "01",
                                "02",
                                "04",
                                "03",
                                "05",
                                "06",
                                "07"
                            ]
                        }
                    ],
                    "2020": [
                        {
                            "1": [
                                "19"
                            ],
                            "4": [
                                "26"
                            ],
                            "5": [
                                "09"
                            ],
                            "6": [
                                "28"
                            ],
                            "9": [
                                "27"
                            ],
                            "10": [
                                "10"
                            ]
                        },
                        {
                            "1": [
                                "01",
                                "24",
                                "25",
                                "26",
                                "27",
                                "28",
                                "29",
                                "30",
                                "23",
                                "31"
                            ],
                            "4": [
                                "04",
                                "05",
                                "06"
                            ],
                            "5": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05"
                            ],
                            "6": [
                                "25",
                                "26",
                                "27"
                            ],
                            "10": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05",
                                "06",
                                "07",
                                "08"
                            ],
                            "2": [
                                "01",
                                "03",
                                "04",
                                "02",
                                "05",
                                "06",
                                "07",
                                "08",
                                "09"
                            ]
                        }
                    ],
                    "2021": [
                        {
                            "2": [
                                "07",
                                "20"
                            ],
                            "4": [
                                "25"
                            ],
                            "5": [
                                "08"
                            ],
                            "9": [
                                "18",
                                "26"
                            ],
                            "10": [
                                "09"
                            ]
                        },
                        {
                            "1": [
                                "01",
                                "02",
                                "03"
                            ],
                            "2": [
                                "11",
                                "13",
                                "12",
                                "14",
                                "15",
                                "16",
                                "17"
                            ],
                            "4": [
                                "03",
                                "04",
                                "05"
                            ],
                            "5": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05"
                            ],
                            "6": [
                                "12",
                                "13",
                                "14"
                            ],
                            "9": [
                                "19",
                                "20",
                                "21"
                            ],
                            "10": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05",
                                "06",
                                "07"
                            ]
                        }
                    ],
                    "2022": [
                        {
                            "1": [
                                "29",
                                "30"
                            ],
                            "4": [
                                "02",
                                "24"
                            ],
                            "5": [
                                "07"
                            ],
                            "10": [
                                "08",
                                "09"
                            ]
                        },
                        {
                            "1": [
                                "01",
                                "02",
                                "03",
                                "31"
                            ],
                            "2": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05",
                                "06"
                            ],
                            "4": [
                                "03",
                                "04",
                                "05",
                                "30"
                            ],
                            "5": [
                                "01",
                                "02",
                                "03",
                                "04"
                            ],
                            "6": [
                                "03",
                                "04",
                                "05"
                            ],
                            "9": [
                                "10",
                                "11",
                                "12"
                            ],
                            "10": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05",
                                "06",
                                "07"
                            ],
                            "12": [
                                "31"
                            ]
                        }
                    ],
                    "2023": [
                        {
                            "1": [
                                "28",
                                "29"
                            ],
                            "10": [
                                "07",
                                "08"
                            ],
                            "4": [
                                "23"
                            ],
                            "5": [
                                "06"
                            ],
                            "6": [
                                "25"
                            ]
                        },
                        {
                            "1": [
                                "01",
                                "02",
                                "21",
                                "22",
                                "23",
                                "24",
                                "25",
                                "26",
                                "27"
                            ],
                            "4": [
                                "05",
                                "29",
                                "30"
                            ],
                            "5": [
                                "01",
                                "02",
                                "03"
                            ],
                            "6": [
                                "22",
                                "23",
                                "24"
                            ],
                            "9": [
                                "29",
                                "30"
                            ],
                            "10": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05",
                                "06"
                            ]
                        }
                    ],
                    "2024": [
                        {
                            "2": [
                                "18",
                                "04"
                            ],
                            "4": [
                                "07",
                                "28"
                            ],
                            "9": [
                                "14",
                                "29"
                            ],
                            "10": [
                                "12"
                            ],
                            "5": [
                                "11"
                            ]
                        },
                        {
                            "1": [
                                "01"
                            ],
                            "2": [
                                "10",
                                "11",
                                "12",
                                "13",
                                "14",
                                "15",
                                "16",
                                "17"
                            ],
                            "4": [
                                "04",
                                "05",
                                "06"
                            ],
                            "5": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05"
                            ],
                            "6": [
                                "10"
                            ],
                            "9": [
                                "15",
                                "16",
                                "17"
                            ],
                            "10": [
                                "01",
                                "03",
                                "04",
                                "05",
                                "06",
                                "07",
                                "02"
                            ]
                        }
                    ],
                    "2025": [
                        {
                            "1": [
                                "26"
                            ],
                            "2": [
                                "08"
                            ],
                            "4": [
                                "27"
                            ],
                            "9": [
                                "28"
                            ],
                            "10": [
                                "11"
                            ]
                        },
                        {
                            "1": [
                                "01",
                                "28",
                                "29",
                                "30",
                                "31"
                            ],
                            "2": [
                                "01",
                                "02",
                                "03",
                                "04"
                            ],
                            "4": [
                                "04",
                                "05",
                                "06"
                            ],
                            "5": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05",
                                "31"
                            ],
                            "6": [
                                "01",
                                "02"
                            ],
                            "10": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05",
                                "06",
                                "07",
                                "08"
                            ]
                        }
                    ]
                }
            ],
            "workspace_workitem_apps": {
                "60498179": [
                    {
                        "id": "1160498179001000003",
                        "system_name": "app_step",
                        "label_name": "\u8282\u70b9",
                        "enabled": "1"
                    }
                ]
            },
            "workitem_permissions": {
                "workitem_app_1160498179001000003": [
                    "stories::add",
                    "stories::quickly_add_story",
                    "stories::add_from_mgr",
                    "stories::addce",
                    "stories::add_support",
                    "stories::quick_add_child_story",
                    "stories::quick_add_story",
                    "stories::edit_draft",
                    "stories::remove_draft",
                    "stroies::worktable_remove_draft",
                    "stories::quickly_add_story_from_tab",
                    "stories::transfer_mindmap_to_story",
                    "storywalls::add_card",
                    "stories::edit",
                    "stories::inline_update",
                    "stories::change_parent_story",
                    "stories::changePriority",
                    "stories::ajax_batch_edit_stories_parent",
                    "stories::update_story_category",
                    "stories::delete",
                    "stories::batch_delete",
                    "entitysort::set_sort",
                    "attachments::story_add",
                    "attachments::story_delete",
                    "attachments::story_download",
                    "stories::copy_story",
                    "imports::import_story",
                    "imports::import_preview_story",
                    "stories::export_story",
                    "exports::export_table",
                    "exports::export_with_select_fields",
                    "exports::export_xls",
                    "stories::move_story",
                    "stories::move_story_in_view_page",
                    "stories::move_story_view_tree",
                    "iterations::stories_batch_process_new",
                    "iterations::stories_batch_process_status",
                    "stories::get_story_status_transfer_info_for_batch",
                    "stories::set_secret_story",
                    "stories::archive"
                ]
            },
            "quick_transfer_workflow_and_status": {
                "story": {
                    "60498179": {
                        "1160498179001000020": [
                            {
                                "origin_name": "done",
                                "cus_name": "\u5df2\u5b8c\u6210",
                                "is_begin": "0",
                                "is_end": "1",
                                "sort": "0"
                            },
                            {
                                "origin_name": "progressing",
                                "cus_name": "\u8fdb\u884c\u4e2d",
                                "is_begin": "0",
                                "is_end": "0",
                                "sort": "0"
                            },
                            {
                                "origin_name": "open",
                                "cus_name": "\u672a\u5f00\u59cb",
                                "is_begin": "1",
                                "is_end": "0",
                                "sort": "0"
                            }
                        ]
                    }
                }
            },
            "status_allow_create_sub_story_map": {
                "60498179": [
                    "planning",
                    "developing",
                    "resolved",
                    "rejected",
                    "status_3",
                    "status_4",
                    "status_5",
                    "open",
                    "progressing",
                    "done"
                ]
            },
            "sw_item_edit_parent_iteration": []
        }
    },
    "meta": {
        "code": "0",
        "message": "success"
    },
    "timestamp": "1749730715"
}







-- 工作区表
CREATE TABLE workspace (
  id VARCHAR(50) PRIMARY KEY COMMENT '工作区ID',
  name VARCHAR(255) COMMENT '工作区名称',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) COMMENT '工作区信息';

-- 缺陷表
CREATE TABLE bug (
  id VARCHAR(50) PRIMARY KEY COMMENT '缺陷ID',
  workspace_id VARCHAR(50) NOT NULL COMMENT '关联工作区',
  title VARCHAR(255) COMMENT '缺陷标题',
  status ENUM('open', 'progressing', 'done') DEFAULT 'open' COMMENT '缺陷状态',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (workspace_id) REFERENCES workspace(id)
) COMMENT '缺陷信息';

-- 测试用例表
CREATE TABLE test_case (
  id VARCHAR(50) PRIMARY KEY COMMENT '测试用例ID',
  name VARCHAR(255) NOT NULL COMMENT '用例名称',
  workspace_id VARCHAR(50) NOT NULL COMMENT '关联工作区',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (workspace_id) REFERENCES workspace(id)
) COMMENT '测试用例基本信息';

-- 测试计划表
CREATE TABLE test_plan (
  id VARCHAR(50) PRIMARY KEY,
  name VARCHAR(255) NOT NULL COMMENT '计划名称',
  workspace_id VARCHAR(50) NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (workspace_id) REFERENCES workspace(id)
) COMMENT '测试计划';

-- 缺陷-测试用例关联表 (核心关联表)
CREATE TABLE bug_test_case (
  bug_id VARCHAR(50) NOT NULL COMMENT '缺陷ID',
  test_case_id VARCHAR(50) NOT NULL COMMENT '测试用例ID',
  test_plan_id VARCHAR(50) COMMENT '测试计划ID',
  executor VARCHAR(100) COMMENT '执行人',
  executed_at DATETIME COMMENT '执行时间',
  result_status VARCHAR(50) COMMENT '执行结果',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (bug_id, test_case_id),
  FOREIGN KEY (bug_id) REFERENCES bug(id),
  FOREIGN KEY (test_case_id) REFERENCES test_case(id),
  FOREIGN KEY (test_plan_id) REFERENCES test_plan(id)
) COMMENT '缺陷关联的测试用例执行记录';


-- 添加查询优化索引
ALTER TABLE bug_test_case ADD INDEX idx_executed_at (executed_at);
ALTER TABLE bug_test_case ADD INDEX idx_result_status (result_status);
ALTER TABLE test_case ADD INDEX idx_workspace (workspace_id);



-- 插入工作区
INSERT INTO workspace (id, name) VALUES 
('60498179', '主要工作区');

-- 插入缺陷
INSERT INTO bug (id, workspace_id, title, status) VALUES 
('1160498179001000009', '60498179', '登录功能缺陷', 'open');

-- 插入测试用例
INSERT INTO test_case (id, workspace_id, name) VALUES
('TC001', '60498179', '用户登录测试'),
('TC002', '60498179', '密码加密测试');

-- 插入测试计划
INSERT INTO test_plan (id, workspace_id, name) VALUES
('TP2024', '60498179', '2024年度测试计划');

-- 关联缺陷和测试用例
INSERT INTO bug_test_case (bug_id, test_case_id, test_plan_id, executor, executed_at, result_status) VALUES
('1160498179001000009', 'TC001', 'TP2024', '张三', '2024-05-15 10:00:00', '通过'),
('1160498179001000009', 'TC002', 'TP2024', '李四', '2024-05-16 14:30:00', '失败');




-- 测试用例主表
CREATE TABLE test_case (
    id VARCHAR(20) PRIMARY KEY COMMENT '测试用例ID',
    workspace_id VARCHAR(20) NOT NULL COMMENT '工作区ID',
    name VARCHAR(255) NOT NULL COMMENT '测试用例名称',
    priority ENUM('高', '中', '低') COMMENT '优先级',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    description TEXT COMMENT '描述',
    status ENUM('open', 'progressing', 'done') DEFAULT 'open' COMMENT '状态'
) COMMENT='测试用例基本信息表';

-- 测试计划表
CREATE TABLE test_plan (
    id VARCHAR(20) PRIMARY KEY COMMENT '测试计划ID',
    workspace_id VARCHAR(20) NOT NULL COMMENT '工作区ID',
    name VARCHAR(255) NOT NULL COMMENT '测试计划名称',
    start_date DATE COMMENT '开始日期',
    end_date DATE COMMENT '结束日期',
    owner VARCHAR(50) COMMENT '负责人'
) COMMENT='测试计划信息表';

-- 测试用例与测试计划关联表
CREATE TABLE test_case_plan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    test_case_id VARCHAR(20) NOT NULL COMMENT '测试用例ID',
    test_plan_id VARCHAR(20) NOT NULL COMMENT '测试计划ID',
    sort_order INT DEFAULT 0 COMMENT '排序',
    FOREIGN KEY (test_case_id) REFERENCES test_case(id),
    FOREIGN KEY (test_plan_id) REFERENCES test_plan(id)
) COMMENT='测试用例与测试计划关联表';

-- 执行记录表
CREATE TABLE test_execution (
    id INT AUTO_INCREMENT PRIMARY KEY,
    test_case_id VARCHAR(20) NOT NULL COMMENT '测试用例ID',
    test_plan_id VARCHAR(20) COMMENT '测试计划ID',
    executor VARCHAR(50) NOT NULL COMMENT '执行人',
    executed_at DATETIME NOT NULL COMMENT '执行时间',
    result_status ENUM('passed', 'failed', 'blocked', 'skipped') NOT NULL COMMENT '执行结果',
    remarks TEXT COMMENT '备注',
    duration INT COMMENT '执行时长(分钟)',
    FOREIGN KEY (test_case_id) REFERENCES test_case(id),
    FOREIGN KEY (test_plan_id) REFERENCES test_plan(id)
) COMMENT='测试执行记录表';

-- 工作区配置表
CREATE TABLE workspace_config (
    workspace_id VARCHAR(20) PRIMARY KEY COMMENT '工作区ID',
    enabled_task BOOLEAN DEFAULT false COMMENT '是否启用任务',
    enabled_board BOOLEAN DEFAULT false COMMENT '是否启用看板',
    enabled_story BOOLEAN DEFAULT true COMMENT '是否启用需求',
    enabled_bug BOOLEAN DEFAULT true COMMENT '是否启用缺陷',
    enabled_tcase BOOLEAN DEFAULT true COMMENT '是否启用测试用例',
    effort_unit ENUM('hour', 'day', 'week') DEFAULT 'day' COMMENT '工作量单位',
    config_data JSON COMMENT '其他配置数据(JSON格式)'
) COMMENT='工作区配置表';

-- 字段定义表
CREATE TABLE field_definition (
    id INT AUTO_INCREMENT PRIMARY KEY,
    workspace_id VARCHAR(20) NOT NULL COMMENT '工作区ID',
    entity_type ENUM('tcase', 'bug', 'story', 'task') NOT NULL COMMENT '实体类型',
    field_name VARCHAR(50) NOT NULL COMMENT '字段名',
    field_label VARCHAR(50) NOT NULL COMMENT '字段标签',
    html_type VARCHAR(20) NOT NULL COMMENT 'HTML控件类型',
    options JSON COMMENT '字段选项(JSON格式)',
    sort_order INT DEFAULT 0 COMMENT '排序',
    is_required BOOLEAN DEFAULT false COMMENT '是否必填'
) COMMENT='字段定义表';

-- 假期表
CREATE TABLE holiday (
    id INT AUTO_INCREMENT PRIMARY KEY,
    workspace_id VARCHAR(20) NOT NULL COMMENT '工作区ID',
    year INT NOT NULL COMMENT '年份',
    month INT NOT NULL COMMENT '月份',
    work_days JSON COMMENT '工作日调整(JSON数组)',
    rest_days JSON COMMENT '休息日(JSON数组)',
    UNIQUE KEY (workspace_id, year, month)
) COMMENT='工作日历表';

-- 权限表
CREATE TABLE permission (
    id INT AUTO_INCREMENT PRIMARY KEY,
    workspace_id VARCHAR(20) NOT NULL COMMENT '工作区ID',
    permission_code VARCHAR(100) NOT NULL COMMENT '权限代码',
    permission_name VARCHAR(100) NOT NULL COMMENT '权限名称',
    category VARCHAR(50) COMMENT '权限类别'
) COMMENT='权限定义表';






-- 插入测试用例
INSERT INTO test_case (id, workspace_id, name, priority) VALUES
('TC1001', '60498179', '登录功能测试', '高'),
('TC1002', '60498179', '支付功能测试', '中');

-- 插入测试计划
INSERT INTO test_plan (id, workspace_id, name) VALUES
('TP2025001', '60498179', '2025Q1核心功能测试计划'),
('TP2025002', '60498179', '支付模块专项测试');

-- 关联测试用例和测试计划
INSERT INTO test_case_plan (test_case_id, test_plan_id) VALUES
('TC1001', 'TP2025001'),
('TC1002', 'TP2025001'),
('TC1002', 'TP2025002');

-- 插入执行记录
INSERT INTO test_execution (test_case_id, test_plan_id, executor, executed_at, result_status) VALUES
('TC1001', 'TP2025001', '张测试', '2025-06-01 10:30:00', 'passed'),
('TC1002', 'TP2025001', '李测试', '2025-06-02 14:15:00', 'failed'),
('TC1002', 'TP2025002', '王测试', '2025-06-03 09:45:00', 'passed');

-- 添加工作区配置
INSERT INTO workspace_config (workspace_id, enabled_tcase, effort_unit) VALUES
('60498179', true, 'day');

-- 添加字段定义
INSERT INTO field_definition (workspace_id, entity_type, field_name, field_label, html_type, options) VALUES
('60498179', 'tcase', 'priority', '优先级', 'select', '[{"value": "高", "label": "高"}, {"value": "中", "label": "中"}, {"value": "低", "label": "低"}]'),
('60498179', 'tcase', 'tcase_name', '测试用例', 'text', NULL);

-- 添加假期数据
INSERT INTO holiday (workspace_id, year, month, work_days, rest_days) VALUES
('60498179', 2025, 1, '[26]', '[1, 28, 29, 30, 31]'),
('60498179', 2025, 2, '[8]', '[1, 2, 3, 4]');

-- 添加权限数据
INSERT INTO permission (workspace_id, permission_code, permission_name) VALUES
('60498179', 'bugs::edit', '编辑缺陷'),
('60498179', 'stories::add', '添加需求'),
('60498179', 'tcase::import_tcase', '导入测试用例');





















