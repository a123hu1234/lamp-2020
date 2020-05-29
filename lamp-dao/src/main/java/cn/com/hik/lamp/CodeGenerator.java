package cn.com.hik.lamp;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/26 11:11
 */
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    @SuppressWarnings("FieldCanBeLocal")
    private String outputDir = "/lamp-mapper.common/src/main/java";

    @SuppressWarnings("FieldCanBeLocal")
    private String author = "cbhu";

    @SuppressWarnings("FieldCanBeLocal")
    private String dsURL = "jdbc:mysql://10.0.40.127:3306/lamp-pdu?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull&useSSL=false";

    @SuppressWarnings("FieldCanBeLocal")
    private String dsDriver = "com.mysql.cj.jdbc.Driver";

    @SuppressWarnings("FieldCanBeLocal")
    private String dsUsername = "root";

    @SuppressWarnings("FieldCanBeLocal")
    private String dsPassword = "hik@123.com";

    @SuppressWarnings("FieldCanBeLocal")
    private String moudleName = "mapper.common";

    @SuppressWarnings("FieldCanBeLocal")
    private String pachageName = "cn.com.hik.lamp";

    // 如果模板引擎是 freemarker
    // private final static String TEMPLATEPATH = "/templates/mapper.xml.ftl";
    // 如果模板引擎是 velocity
    @SuppressWarnings("FieldCanBeLocal")
    private String templatePath = "/templates/mapper.xml.vm";

    //"表名，多个英文逗号分割"
    @SuppressWarnings("FieldCanBeLocal")
    private String tables = "lamp_menu,lamp_role,lamp_user,lamp_role_menu";


    private void run() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        //全局配置
        GlobalConfig gc = getGlobalConfig();
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = getDataSourceConfig();
        mpg.setDataSource(dsc);

        //包配置
        PackageConfig pc = getPackageConfig();
        mpg.setPackageInfo(pc);

        //自定义配置
        InjectionConfig cfg = getInjectionCOnfig();
        mpg.setCfg(cfg);

        //模板配置
        TemplateConfig templateConfig = getTemplateConfig();
        mpg.setTemplate(templateConfig);

        StrategyConfig strategyConfig = getStrategyConfig();
        mpg.setStrategy(strategyConfig);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }


    private GlobalConfig getGlobalConfig() {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + outputDir);
        gc.setAuthor(author);
        gc.setOpen(false);
        return gc;
    }

    private PackageConfig getPackageConfig() {
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moudleName);
        pc.setParent(pachageName);
        return pc;
    }

    private DataSourceConfig getDataSourceConfig() {

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dsURL);
        dsc.setDriverName(dsDriver);
        dsc.setUsername(dsUsername);
        dsc.setPassword(dsPassword);
        return dsc;
    }

    private InjectionConfig getInjectionCOnfig() {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String projectPath = System.getProperty("user.dir");
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/lamp-mapper.common/src/main/resources/mapper/" + moudleName
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

            /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */

        cfg.setFileOutConfigList(focList);


        return cfg;
    }

    private TemplateConfig getTemplateConfig() {
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别

        //templateConfig.setEntity("templates/entity2.java");
        //templateConfig.setService();
        //templateConfig.setController();
        templateConfig.setController(null);
        templateConfig.setXml(null);
        return templateConfig;
    }

    private StrategyConfig getStrategyConfig() {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(false);
        // 公共父类
        // 写于父类中的公共字段
        //  strategy.setSuperEntityColumns("id");
        strategy.setInclude(tables.split(","));
        strategy.setControllerMappingHyphenStyle(false);

        return strategy;
    }

    public static void main(String[] args) {

        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.run();

    }

}
