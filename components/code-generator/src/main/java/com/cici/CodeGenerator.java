package com.cici;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;

/**
 * @author pig
 * @date 2017/10/29
 */
public class CodeGenerator {


    public static void main(String[] args) {
        String outputDir = "D:/source";
        List<TableFill> tableFillList = new ArrayList<>();

        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);

        gc.setAuthor("cici");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
//        strategy.setSuperControllerClass("com.zwx.api.v2.controller.BaseController");
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setVersionFieldName("version");
        strategy.setSuperEntityClass("DelFlagEntity");
        mpg.setStrategy(strategy);

        mpg.setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        this.setMap(map);
                    }
                }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig("/templates/mapper.xml.vm") {
                    // 自定义输出文件目录
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return gc.getOutputDir()+"/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
                    }
                }))
        ).setStrategy(
                // 策略配置
                new StrategyConfig()
                        // .setCapitalMode(true)// 全局大写命名   0 2 +
                        // .setDbColumnUnderline(true)//全局下划线命名
                        //.setTablePrefix(new String[]{"bmd_", "mp_"})// 此处可以修改为您的表前缀
                        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                        // .setInclude(new String[] { "user" }) // 需要生成的表
                        // .setExclude(new String[]{"test"}) // 排除生成的表
                        // 自定义实体父类
                        .setSuperEntityClass("com.cici.base.DelFlagEntity")
                        // 自定义实体，公共字段
                        .setSuperEntityColumns(new String[]{"create_date", "create_by", "update_date", "update_by", "version", "del_flag"})
                        .setTableFillList(tableFillList)
                        // 自定义 mapper 父类
//                        .setSuperMapperClass("com.zwx.mybatis.SuperMapper")
                        // 自定义 service 父类
                        // .setSuperServiceClass("com.baomidou.demo.TestService")
                        // 自定义 service 实现类父类
                        // .setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl")
                        // 自定义 controller 父类
                        // .setSuperControllerClass("com.baomidou.demo.TestController")
                        // 【实体】是否生成字段常量（默认 false）
                        // public static final String ID = "test_id";
                        // .setEntityColumnConstant(true)
                        // 【实体】是否为构建者模型（默认 false）
                        // public User setName(String name) {this.name = name; return this;}
                        // .setEntityBuilderModel(true)
                        // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                        .setEntityLombokModel(true)
                        //    .setVersionFieldName("version")
                        // Boolean类型字段是否移除is前缀处理
//                 .setEntityBooleanColumnRemoveIsPrefix(true)
                        .setRestControllerStyle(true)
                        .setControllerMappingHyphenStyle(true)
        ).setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig().setXml(null)
                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                // .setController("...");
                // .setEntity("...");
                // .setMapper("...");
                // .setXml("...");
                // .setService("...");
                // .setServiceImpl("...");
        );
        ;

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.cici");// 自定义包路径
        pc.setController("controller");// 这里是控制器包名，默认 web
        mpg.setPackageInfo(pc);

        //生成java相关
        mpg.execute();
    }
}
