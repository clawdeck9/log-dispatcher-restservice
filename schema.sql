CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1wdpsed5kna2y38hnbgrnhi5b` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
CREATE TABLE `web_log_paragraph` (
  `id` bigint(20) NOT NULL,
  `accessed_date` datetime DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `index` int(11) NOT NULL,
  `full_line` longtext,
  `tag` varchar(15) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `tag_entity` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmnklgf6cgf9cpdvkit9soa6ju` (`tag_entity`),
  KEY `FKl5amjv9o15xfmf4a8543jm2bd` (`tag`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
