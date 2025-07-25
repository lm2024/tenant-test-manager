# 号段形式ID生成服务实施计划

- [ ] 1. 创建项目结构和Maven配置
  - [ ] 1.1 创建segment-id-generator-starter模块目录结构
    - 创建标准Maven项目结构：src/main/java, src/main/resources, src/test/java
    - 创建包结构：com.segment.id.{config, service, repository, entity, dto, exception, util}
    - 创建META-INF/spring.factories自动配置文件
    - _需求: 4.1, 4.2_
  
  - [ ] 1.2 配置pom.xml依赖
    - 添加Spring Boot 2.7.x相关依赖（starter、web、data-jpa）
    - 添加Redisson 3.17.0和redisson-spring-boot-starter依赖用于Redis连接
    - 添加MySQL驱动8.0.33和Druid 1.2.20连接池依赖
    - 添加Spring Data JPA和Hibernate依赖用于数据库操作
    - 添加Jackson、Hutool、Lombok、Swagger等工具依赖
    - 添加spring-boot-configuration-processor支持配置提示
    - 移除tenant-routing-starter依赖，租户处理由上层调用方负责
    - _需求: 4.1_
  
  - [ ] 1.3 创建核心DTO和请求响应对象
    - 创建IdGenerateRequest、BatchIdGenerateRequest请求对象
    - 创建SequenceConfigRequest、SequenceConfigResponse对象
    - 创建SequenceInfo、SequenceSegment、ApiResponse通用响应对象
    - 添加JSR-303验证注解和Swagger文档注解
    - _需求: 1.1, 4.3_

- [ ] 2. 实现数据模型和数据库操作
  - [ ] 2.1 创建序列配置实体类和Repository
    - 创建SequenceConfig实体类，使用JPA注解@Entity、@Table、@Column等
    - 包含租户ID、业务类型、生成类型、前缀、后缀、自定义模板等完整字段
    - 创建SequenceConfigRepository接口，继承JpaRepository<SequenceConfig, Long>
    - 实现findByTenantIdAndBusinessType、findByTenantId等查询方法
    - 添加@Modifying和@Query注解实现原子性更新操作
    - 配置唯一约束和索引优化查询性能
    - _需求: 1.5, 3.1, 3.2_

  - [ ] 2.2 创建序列统计实体类和Repository
    - 创建SequenceStatistics实体类，使用JPA注解配置表映射
    - 包含生成次数、批量生成次数、总生成数量、平均响应时间等统计字段
    - 创建SequenceStatisticsRepository接口，继承JpaRepository
    - 实现按日期、租户、业务类型的统计查询方法
    - 使用@Query注解实现复杂的统计更新和删除操作
    - 配置复合索引优化统计查询性能
    - _需求: 5.1, 5.2_

  - [ ] 2.3 创建JPA配置和数据库初始化脚本
    - 创建SegmentIdJpaConfig配置类，配置JPA和Hibernate属性
    - 配置Druid数据源，参考现有项目的连接池配置
    - 配置JPA Repository扫描路径和事务管理器
    - 编写sequence_config表的DDL脚本，包含完整字段定义、唯一约束和索引
    - 编写sequence_statistics表的DDL脚本，支持按日期分区的统计查询
    - 创建data.sql初始化脚本，包含常用业务类型的默认配置数据
    - 配置Hibernate的ddl-auto策略和SQL日志输出
    - _需求: 1.5, 3.1_

- [ ] 3. 实现Redisson缓存管理
  - [ ] 3.1 创建Redisson配置类
    - 创建SegmentIdRedissonConfig配置类，参考tenant-routing-starter的RedissonConfig
    - 配置单节点Redis连接，支持密码认证和连接池设置
    - 创建专用的RedissonClient Bean，避免与其他模块冲突
    - 配置JSON序列化器，支持复杂对象的缓存
    - _需求: 2.1, 2.2_

  - [ ] 3.2 实现号段缓存管理器
    - 创建SegmentCacheManager类，使用Redisson客户端
    - 实现号段缓存的存储和获取方法，使用RBucket存储号段信息
    - 实现分布式锁机制，使用RLock防止并发问题
    - 实现缓存Key的统一管理，支持租户隔离
    - 实现缓存TTL管理，防止缓存堆积
    - _需求: 2.1, 2.2, 2.3, 3.1_

  - [ ] 3.3 实现缓存预热和清理机制
    - 实现ApplicationRunner，在服务启动时清理过期缓存
    - 实现缓存预热机制，预加载热点业务类型的号段
    - 实现定时任务，定期清理无效缓存和统计信息
    - 实现缓存监控，记录缓存命中率和性能指标
    - _需求: 2.2, 2.3, 5.1_

- [ ] 4. 实现核心ID生成算法
  - [ ] 4.1 实现ID生成算法工厂
    - 创建IdGeneratorFactory工厂类，管理四种生成类型
    - 实现NumericIdGenerator：纯数字ID生成器，支持自定义长度和零填充
    - 实现PrefixIdGenerator：前缀+数字ID生成器，支持自定义前缀
    - 实现SuffixIdGenerator：数字+后缀ID生成器，支持自定义后缀
    - 实现CustomIdGenerator：自定义模板ID生成器，支持占位符替换
    - _需求: 1.1, 1.2, 1.3, 1.4_

  - [ ] 4.2 实现号段管理器
    - 创建SequenceManager类，使用@Service和@Transactional注解
    - 租户ID通过方法参数传入，由上层调用方处理租户上下文
    - 实现getNextSegment方法，使用JPA Repository查询和更新号段信息
    - 实现updateSequenceValue方法，使用@Modifying注解实现原子性更新
    - 实现号段用尽检查和自动续期机制，支持事务回滚
    - 使用JPA的乐观锁或悲观锁确保并发安全
    - 添加统计信息更新逻辑，记录生成次数和性能指标
    - _需求: 2.3, 2.4_

  - [ ] 4.3 实现核心ID生成器
    - 创建SegmentIdGenerator主类，整合缓存管理器和序列管理器
    - 实现generateId方法：先从缓存获取号段，缓存未命中时从数据库加载
    - 实现generateBatchIds方法：高性能批量生成，支持跨号段生成
    - 实现线程安全的AtomicLong递增逻辑
    - 添加性能监控和异常处理
    - _需求: 2.5, 4.3, 4.4_

- [ ] 5. 实现租户隔离和参数传递
  - [ ] 5.1 实现租户参数传递机制
    - 修改所有核心方法，通过参数传递租户ID
    - 在所有数据库操作中使用传入的租户ID进行过滤
    - 在缓存Key中包含租户ID，实现缓存隔离
    - 确保所有API接口都支持租户ID参数传递（通过@RequestHeader获取）
    - _需求: 3.1, 3.2, 3.3_

  - [ ] 5.2 实现租户级别的序列管理
    - 修改SequenceManager，所有操作都基于传入的租户ID
    - 实现租户级别的序列配置初始化和管理
    - 实现租户级别的统计信息收集和查询
    - 添加租户隔离的缓存管理机制
    - _需求: 3.1, 3.2, 3.3_

  - [ ] 5.3 实现基础安全控制
    - 实现基础的参数验证和异常处理
    - 添加操作审计日志记录
    - 实现基础的资源配额限制（如每日生成ID数量限制）
    - 由上层调用方负责租户权限验证和访问控制
    - _需求: 5.3_

- [ ] 6. 实现REST API接口
  - [ ] 6.1 创建ID生成控制器
    - 创建SegmentIdController，添加Swagger注解和API文档
    - 实现POST /api/v1/segment-id/generate单个ID生成接口
    - 实现POST /api/v1/segment-id/generate/batch批量ID生成接口
    - 实现GET /api/v1/segment-id/sequence/{businessType}/info序列信息查询接口
    - 实现POST /api/v1/segment-id/cache/preload/{businessType}缓存预热接口
    - 实现DELETE /api/v1/segment-id/cache/clear缓存清理接口
    - 添加参数验证、异常处理和统一响应格式
    - _需求: 4.3, 5.1_

  - [ ] 6.2 创建序列配置管理控制器
    - 创建SequenceConfigController，提供序列配置管理功能
    - 实现POST /api/v1/sequence-config创建序列配置接口
    - 实现PUT /api/v1/sequence-config/{businessType}更新序列配置接口
    - 实现GET /api/v1/sequence-config/{businessType}获取序列配置接口
    - 实现DELETE /api/v1/sequence-config/{businessType}删除序列配置接口
    - 实现GET /api/v1/sequence-config分页查询序列配置接口
    - 添加租户隔离和权限验证
    - _需求: 5.1, 5.2, 5.3_

  - [ ] 6.3 创建统计和监控接口
    - 创建SequenceStatisticsController，提供统计信息查询
    - 实现GET /api/v1/sequence-statistics/{businessType}业务类型统计接口
    - 实现GET /api/v1/sequence-statistics/tenant租户统计接口
    - 实现GET /api/v1/sequence-statistics/health基础健康检查接口
    - 移除Spring Boot Actuator依赖，使用自定义健康检查
    - _需求: 5.1, 5.2_

- [ ] 7. 实现Spring Boot Starter自动配置
  - [ ] 7.1 创建配置属性类
    - 创建SegmentIdProperties配置属性类，使用@ConfigurationProperties注解
    - 定义Redis配置、数据库配置、缓存配置等嵌套配置类
    - 移除tenant-routing-starter相关配置，简化配置结构
    - 设置合理的默认值和配置验证规则
    - 支持不同环境的配置文件：application-dev.yml, application-prod.yml
    - _需求: 4.2_

  - [ ] 7.2 创建自动配置类
    - 创建SegmentIdAutoConfiguration主配置类
    - 使用@ConditionalOnProperty控制启用条件
    - 导入SegmentIdRedissonConfig、SegmentIdJpaConfig等子配置类
    - 配置所有核心Bean的自动装配和依赖注入
    - 配置JPA Repository扫描路径：@EnableJpaRepositories
    - 配置事务管理器和数据源
    - 添加@PostConstruct初始化方法和启动日志
    - _需求: 4.1, 4.2_

  - [ ] 7.3 创建META-INF配置文件
    - 创建META-INF/spring.factories文件，注册自动配置类
    - 创建META-INF/additional-spring-configuration-metadata.json配置元数据
    - 创建application.yml配置模板，包含所有可配置项的说明
    - 创建logback-spring.xml日志配置，参考现有项目的日志格式
    - _需求: 4.1, 4.2_

  - [ ] 7.4 创建便捷使用模板类
    - 创建SegmentIdTemplate模板类，提供简化的API调用方式
    - 创建@SegmentId注解，支持方法级别的自动ID生成
    - 创建SegmentIdAspect切面，实现注解驱动的ID注入
    - 提供常用的工具方法和最佳实践示例
    - _需求: 4.2, 4.3_

- [ ] 8. 实现异常处理和日志记录
  - [ ] 8.1 创建异常处理体系
    - 创建SegmentIdException基础异常类和子异常类
    - 定义SequenceExhaustedException、SequenceNotConfiguredException等具体异常
    - 创建GlobalExceptionHandler全局异常处理器，统一异常响应格式
    - 实现异常恢复和降级机制：缓存不可用时降级到数据库直接生成
    - 添加异常监控和告警机制
    - _需求: 4.4_

  - [ ] 8.2 实现日志记录系统
    - 参考my-product-tenant的logback-spring.xml配置日志格式
    - 实现结构化日志，支持ELK日志收集和分析
    - 添加关键操作的审计日志：ID生成、配置变更、缓存操作等
    - 实现性能监控日志，记录响应时间、QPS、缓存命中率等指标
    - 租户信息通过MDC传递到日志中，由上层调用方设置
    - _需求: 5.3, 5.4_

  - [ ] 8.3 实现监控和指标收集
    - 移除Micrometer和Spring Boot Actuator依赖
    - 实现自定义监控指标收集：ID生成速率、缓存命中率、数据库连接状态等
    - 创建自定义健康检查器，检查Redis和数据库连接状态
    - 实现告警机制，当序列使用率超过阈值时发送告警
    - 提供简单的监控数据查询接口
    - _需求: 5.1, 5.2_

- [ ] 9. 创建配置文件和文档
  - [ ] 9.1 创建完整的配置文件模板
    - 创建src/main/resources/application.yml主配置文件模板
    - 创建application-dev.yml开发环境配置，包含本地MySQL和Redis配置
    - 创建application-prod.yml生产环境配置，包含集群配置和性能优化参数
    - 参考现有项目配置格式，确保配置一致性
    - 添加详细的配置注释和说明
    - _需求: 4.2_

  - [ ] 9.2 创建SQL初始化脚本
    - 创建sql/segment_id_init.sql数据库初始化脚本
    - 在tenant_center库中创建sequence_config和sequence_statistics表的完整DDL
    - 创建必要的索引和约束
    - 提供常用业务类型的初始化数据
    - 支持多租户场景的数据隔离
    - _需求: 1.5, 3.1_

  - [ ] 9.3 编写完整的使用文档
    - 创建README.md主文档，包含功能介绍、快速开始、配置说明
    - 创建API文档，详细说明所有REST接口的使用方法
    - 创建集成指南，说明如何在其他项目中集成使用
    - 创建配置参数说明文档，列出所有可配置项和默认值
    - 创建故障排查文档，包含常见问题和解决方案
    - 创建性能调优文档，提供生产环境的最佳实践
    - _需求: 6.1, 6.3, 6.4_

- [ ] 10. 在my-test-execute中创建测试控制器
  - [ ] 10.1 集成segment-id-generator-starter到my-test-execute
    - 在my-test-execute的pom.xml中添加segment-id-generator-starter依赖
    - 在application.yml中添加segment-id配置，使用tenant_center数据库
    - 配置数据源指向tenant_center库：jdbc:mysql://localhost:3306/tenant_center
    - 移除tenant-routing-starter相关配置，简化集成
    - 配置测试环境的数据库表和初始数据
    - _需求: 6.2_

  - [ ] 10.2 创建测试控制器
    - 创建SegmentIdTestController，提供完整的测试接口
    - 实现GET /test/segment-id/generate/{businessType}单个ID生成测试
    - 实现POST /test/segment-id/batch批量ID生成测试
    - 实现GET /test/segment-id/performance/{businessType}性能测试接口
    - 实现POST /test/segment-id/config序列配置测试接口
    - 实现GET /test/segment-id/cache/info缓存状态查询接口
    - 添加Swagger文档注解，方便测试调用
    - _需求: 6.2_

  - [ ] 10.3 创建测试用例和验证脚本
    - 编写JUnit单元测试，验证四种ID生成算法的正确性
    - 编写集成测试，验证完整的ID生成流程
    - 编写并发测试，验证多线程环境下的线程安全性
    - 编写性能测试，验证QPS和响应时间指标
    - 创建Postman测试集合，提供API测试脚本
    - _需求: 6.2_

- [ ] 11. 实现监控和运维功能
  - [ ] 11.1 实现健康检查和指标监控
    - 创建自定义健康检查接口，提供/api/v1/segment-id/health端点
    - 创建SegmentIdHealthChecker，检查Redis连接、数据库连接、序列状态
    - 移除Spring Boot Actuator和Micrometer依赖
    - 实现自定义指标收集：segment.id.generate.count、segment.id.cache.hit.rate等
    - 创建监控数据查询接口，支持外部监控系统集成
    - _需求: 5.1, 5.2_

  - [ ] 11.2 实现管理和维护接口
    - 创建SegmentIdManagementController管理控制器
    - 实现GET /management/segment-id/sequences序列状态查询接口
    - 实现POST /management/segment-id/sequences/{businessType}/reset序列重置接口
    - 实现DELETE /management/segment-id/cache缓存清理接口
    - 实现GET /management/segment-id/diagnostics故障诊断接口
    - 实现POST /management/segment-id/recovery故障恢复接口
    - 添加管理接口的权限控制和操作审计
    - _需求: 5.3, 5.4_

  - [ ] 11.3 实现运维工具和脚本
    - 创建数据库维护脚本：清理过期统计数据、重建索引等
    - 创建缓存维护脚本：批量清理、预热、监控等
    - 创建性能分析工具：慢查询分析、缓存命中率分析等
    - 创建故障恢复脚本：自动重启、数据修复等
    - 提供运维手册和操作指南
    - _需求: 5.3, 5.4_

- [ ] 12. 性能优化和最终测试
  - [ ] 12.1 进行性能优化
    - 优化数据库连接池配置：调整Druid连接池参数，设置合适的最大连接数和超时时间
    - 优化Redis连接配置：调整Redisson连接池参数，优化序列化方式
    - 优化缓存策略：调整缓存TTL、预加载策略、批量操作大小
    - 优化并发处理：使用AtomicLong、ConcurrentHashMap等高性能并发工具
    - 优化内存使用：减少对象创建、使用对象池、及时释放资源
    - 优化数据库查询：添加必要索引、优化SQL语句、使用批量操作
    - _需求: 2.4, 2.5_

  - [ ] 12.2 进行全面测试验证
    - 执行功能测试：验证四种ID生成类型、租户隔离、配置管理等所有功能
    - 执行性能测试：使用JMeter测试单线程和多线程场景，验证QPS达到5万+
    - 执行并发测试：模拟高并发场景，验证线程安全性和数据一致性
    - 执行稳定性测试：长时间运行测试，验证内存泄漏、连接泄漏等问题
    - 执行容错测试：模拟Redis故障、数据库故障等异常场景
    - 执行兼容性测试：验证与现有系统的集成兼容性
    - _需求: 6.1, 6.2, 6.3, 6.4_

  - [ ] 12.3 编写部署和上线文档
    - 创建部署指南：包含环境要求、依赖安装、配置说明等
    - 创建上线检查清单：包含功能验证、性能验证、监控配置等
    - 创建回滚方案：包含数据备份、服务回滚、故障恢复等
    - 创建运维手册：包含日常维护、故障处理、性能调优等
    - 提供生产环境的配置模板和最佳实践
    - _需求: 6.3, 6.4_