package me.adairh.chidori;

import de.slikey.effectlib.Effect;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.util.MathUtils;
import de.slikey.effectlib.util.ParticleEffect;
import de.slikey.effectlib.util.VectorUtils;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class chidori
  extends Effect
{
  public ParticleEffect particle = ParticleEffect.WATER_DROP;
  public int particles = 150;
  public int particlesPerIteration = 10;
  public float size = 1.0F;
  public float xFactor = 1.0F;
  public float yFactor = 2.0F;
  public float zFactor = 1.0F;
  public float xOffset;
  public float yOffset = 0.8F;
  public float zOffset;
  public double xRotation;
  public double yRotation;
  public double zRotation = 0.0D;
  protected int step;
  
  public chidori(EffectManager effectManager)
  {
    super(effectManager);
    this.type = EffectType.REPEATING;
    this.iterations = 500;
    this.period = 1;
  }
  
  public void onRun()
  {
    Vector vector = new Vector();
    Location location = getLocation();
    for (int i = 0; i < this.particlesPerIteration; i++)
    {
      this.step += 1;
      
      float t = 3.1415927F / this.particles * this.step;
      float r = MathUtils.sin(t) * this.size;
      float s = 6.2831855F * t;
      
      vector.setX(this.xFactor * r * MathUtils.cos(s) + this.xOffset);
      vector.setZ(this.zFactor * r * MathUtils.sin(s) + this.zOffset);
      vector.setY(this.yFactor * this.size * MathUtils.cos(t) + this.yOffset);
      
      VectorUtils.rotateVector(vector, this.xRotation, this.yRotation, this.zRotation);
      
      display(this.particle, location.add(vector));
      location.subtract(vector);
    }
  }
}
