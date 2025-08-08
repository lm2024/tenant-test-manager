/**
 * Redis列表缓存组件
 * 
 * <p>提供基于注解的透明缓存功能，支持：</p>
 * <ul>
 *   <li>智能分页缓存 - 只缓存前5页数据</li>
 *   <li>租户数据隔离 - 多租户环境下的缓存隔离</li>
 *   <li>自动失效机制 - 数据变更时自动清除相关缓存</li>
 *   <li>优雅降级处理 - Redis故障时自动回退到数据库</li>
 *   <li>丰富监控指标 - 缓存命中率、响应时间等统计</li>
 * </ul>
 * 
 * @author Kiro
 * @version 1.0.0
 */
package com.common.redis.cache;