
--
-- 转存表中的数据 `oauth_client_details`
--

INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `web_server_redirect_uri`, `create_time`) VALUES
('c1', '', '$2a$10$Xp6SlIIdUcWPIDKAJwuin.Nnm.RQWuJUFZ/2jzWtG/5XyvlMwVjH2', 'all', 'authorization_code,password,client_credentials,implicit,refresh_token,platform_grant', '', 72000, 72000, NULL, NULL, 'http://www.baidu.com', '2024-06-05 00:00:00.000');
