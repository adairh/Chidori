package me.adairh.chidori;

import de.slikey.effectlib.Effect;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.Location;

public class chidori2
  extends Effect
{
  public ParticleEffect particle = ParticleEffect.WATER_WAKE;
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
  
  public chidori2(EffectManager effectManager)
  {
    super(effectManager);
    this.type = EffectType.REPEATING;
    this.iterations = 500;
    this.period = 1;
  }
  
  public void onRun()
  {
    Location location = getLocation();
   
      
      display(this.particle, location.add(-0.6,-1.0,0.6));
      location.subtract(-0.6,-1.0,0.6);
      display(this.particle, location.add(-0.7,-1.0,0.7));
      location.subtract(-0.7,-1.0,0.7);
      display(this.particle, location.add(-0.5,-1.0,0.5));
      location.subtract(-0.5,-1.0,0.5);
      display(this.particle, location.add(-0.6,-1.1,0.6));
      location.subtract(-0.6,-1.0,0.6);
      display(this.particle, location.add(-0.7,-1.1,0.7));
      location.subtract(-0.7,-1.0,0.7);
      display(this.particle, location.add(-0.5,-1.1,0.5));
      location.subtract(-0.5,-1.0,0.5);
      display(this.particle, location.add(-0.6,-0.9,0.6));
      location.subtract(-0.6,-1.0,0.6);
      display(this.particle, location.add(-0.7,-0.9,0.7));
      location.subtract(-0.7,-1.0,0.7);
      display(this.particle, location.add(-0.5,-0.9,0.5));
      location.subtract(-0.5,-1.0,0.5);
  }
}
