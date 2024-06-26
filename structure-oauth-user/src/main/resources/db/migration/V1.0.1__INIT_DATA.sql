--
-- 转存表中的数据 `oauth_user`
--

INSERT INTO `user` (`id`, `username`, `password`,`nick_name`,`avatar`, `is_unexpired`, `is_enabled`, `is_unlocked`, `is_deleted`, `create_time`, `update_time`) VALUES
(1, 'admin', '$2a$10$woD2QerhP5LTaGj4G37UAe6pEPOYT3fOSA7VzIpwxlPgqgGaAkpz6','管理员','', 1, 1, 1, 0, '2024-06-19 00:00:00.000', '2024-06-11 00:00:00.000');

-- --------------------------------------------------------
