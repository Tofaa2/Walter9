/*     */ package cheaters.get.banned.config;
/*     */ 
/*     */ import cheaters.get.banned.Shady;
/*     */ import cheaters.get.banned.config.components.ConfigInput;
/*     */ import cheaters.get.banned.config.components.Scrollbar;
/*     */ import cheaters.get.banned.config.settings.Setting;
/*     */ import cheaters.get.banned.utils.FontUtils;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ 
/*     */ public class ConfigGui
/*     */   extends GuiScreen
/*     */ {
/*  21 */   private int columnWidth = 300;
/*  22 */   public static ArrayList<Setting> settings = filterSettings();
/*     */   
/*     */   private int prevMouseY;
/*  25 */   private int scrollOffset = 0;
/*     */   
/*     */   private boolean scrolling = false;
/*     */   private ResourceLocation logo;
/*     */   private Scrollbar scrollbar;
/*  30 */   private Integer prevWidth = null;
/*  31 */   private Integer prevHeight = null;
/*     */   
/*     */   public ConfigGui() {
/*  34 */     this(new ResourceLocation("shadyaddons:logo.png"));
/*     */   }
/*     */   
/*     */   public ConfigGui(ResourceLocation logo) {
/*  38 */     this.logo = logo;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/*  44 */     func_146276_q_();
/*  45 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */     
/*  47 */     mouseMoved(mouseY);
/*     */ 
/*     */     
/*  50 */     GlStateManager.func_179124_c(255.0F, 255.0F, 255.0F);
/*  51 */     Shady.mc.func_110434_K().func_110577_a(this.logo);
/*  52 */     func_146110_a(this.field_146294_l / 2 - 143, 24 - this.scrollOffset, 0.0F, 0.0F, 286, 40, 286.0F, 40.0F);
/*  53 */     func_73732_a(Shady.mc.field_71466_p, (Shady.BETA ? "Beta ✦ " : "Stable ✦ ") + "2.1.0-pre2", this.field_146294_l / 2, 67 - this.scrollOffset, -1);
/*     */ 
/*     */     
/*  56 */     for (int i = 0; i < settings.size(); i++) {
/*  57 */       Setting setting = settings.get(i);
/*     */       
/*  59 */       int x = getOffset();
/*  60 */       int y = this.columnWidth / 3 + i * 15 - this.scrollOffset;
/*     */       
/*  62 */       x += setting.getIndent(0);
/*     */       
/*  64 */       if (setting.parent == null && i > 0)
/*     */       {
/*  66 */         func_73734_a(x, y - 3, getOffset() + this.columnWidth, y - 2, ConfigInput.transparent.getRGB());
/*     */       }
/*     */ 
/*     */       
/*  70 */       Shady.mc.field_71466_p.func_78276_b(((setting instanceof cheaters.get.banned.config.settings.BooleanSetting && ((Boolean)setting.get(Boolean.class)).booleanValue()) ? "§a" : "§f") + setting.name, x, y + 1, -1);
/*  71 */       if (setting.credit != null) {
/*  72 */         int settingNameWidth = Shady.mc.field_71466_p.func_78256_a(setting.name + " ");
/*  73 */         GlStateManager.func_179137_b(0.0D, 1.8D, 0.0D);
/*  74 */         FontUtils.drawScaledString("§7" + setting.credit, 0.8F, x + settingNameWidth, y + 1);
/*  75 */         GlStateManager.func_179137_b(0.0D, -1.8D, 0.0D);
/*     */       } 
/*     */     } 
/*     */     
/*  79 */     if (this.prevHeight != null && this.prevWidth != null && (this.prevWidth.intValue() != this.field_146294_l || this.prevHeight.intValue() != this.field_146295_m)) {
/*  80 */       Shady.mc.func_147108_a(new ConfigGui(this.logo));
/*     */     }
/*     */     
/*  83 */     this.prevWidth = Integer.valueOf(this.field_146294_l);
/*  84 */     this.prevHeight = Integer.valueOf(this.field_146295_m);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  89 */     this.field_146292_n.clear();
/*     */     
/*  91 */     for (int i = 0; i < settings.size(); i++) {
/*  92 */       Setting setting = settings.get(i);
/*     */       
/*  94 */       int x = getOffset() + this.columnWidth;
/*  95 */       int y = this.columnWidth / 3 + i * 15 - this.scrollOffset;
/*     */       
/*  97 */       this.field_146292_n.add(ConfigInput.createButtonForSetting(setting, x, y));
/*     */     } 
/*     */     
/* 100 */     int viewport = this.field_146295_m - 100 - 10;
/* 101 */     int contentHeight = settings.size() * 15;
/* 102 */     int scrollbarX = getOffset() + this.columnWidth + 10;
/*     */     
/* 104 */     this.scrollbar = new Scrollbar(viewport, contentHeight, this.scrollOffset, scrollbarX, this.scrolling);
/* 105 */     this.field_146292_n.add(this.scrollbar);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146284_a(GuiButton button) {
/* 110 */     if (button instanceof Scrollbar) {
/* 111 */       this.scrolling = true;
/*     */     } else {
/* 113 */       settings.clear();
/* 114 */       settings = filterSettings();
/*     */     } 
/* 116 */     func_73866_w_();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146286_b(int mouseX, int mouseY, int state) {
/* 121 */     this.scrolling = false;
/* 122 */     super.func_146286_b(mouseX, mouseY, state);
/*     */   }
/*     */   
/*     */   private void scrollScreen(int scrollAmount) {
/* 126 */     int viewport = this.field_146295_m - 100 - 10;
/* 127 */     int contentHeight = settings.size() * 15;
/*     */     
/* 129 */     this.scrollOffset = MathHelper.func_76125_a(this.scrollOffset + scrollAmount, 0, contentHeight - viewport);
/* 130 */     func_73866_w_();
/*     */   }
/*     */   
/*     */   private void mouseMoved(int mouseY) {
/* 134 */     if (this.scrolling) scrollScreen(mouseY - this.prevMouseY); 
/* 135 */     this.prevMouseY = mouseY;
/*     */   }
/*     */   
/*     */   private static ArrayList<Setting> filterSettings() {
/* 139 */     ArrayList<Setting> newSettings = new ArrayList<>();
/*     */     
/* 141 */     for (Setting setting : Shady.settings) {
/* 142 */       if (setting.parent == null) {
/* 143 */         newSettings.add(setting);
/*     */         
/*     */         continue;
/*     */       } 
/* 147 */       if (((Boolean)setting.parent.get(Boolean.class)).booleanValue()) {
/* 148 */         newSettings.add(setting);
/*     */       }
/*     */     } 
/*     */     
/* 152 */     return newSettings;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146274_d() throws IOException {
/* 157 */     if (Mouse.getEventDWheel() != 0) {
/* 158 */       scrollScreen(Integer.signum(Mouse.getEventDWheel()) * -10);
/*     */     }
/* 160 */     super.func_146274_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146281_b() {
/* 165 */     ConfigLogic.save();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_73868_f() {
/* 170 */     return false;
/*     */   }
/*     */   
/*     */   private int getOffset() {
/* 174 */     return (this.field_146294_l - this.columnWidth) / 2;
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\config\ConfigGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */