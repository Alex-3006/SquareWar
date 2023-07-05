package org.napf.squarewar.core;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Physics {

	public static void physicsCycle(ArrayList<PhysicsObject> physicsObjects) {
		
		
		PhysicsObject player = (PhysicsObject)GameController.getInstance().findGameObject("PlayerTank");
		for (int i = 0; i < physicsObjects.size(); i++) {
			if (physicsObjects.get(i).getName().equals("PlayerTank"))
				continue;
			
			RayCollisionInfo rci = DynamicRectVsRect(player, physicsObjects.get(i));
			if (rci != null) {
				//player.setVelX(0);
				//player.setVelY(0);
				//new GameObject(rci.getContactPointX(), rci.getContactPointY(), "Test", new Rectangle(0.1, 0.1, Color.BLACK));
			
				player.setVelX(player.getVelX() + rci.getContactNormalX() * Math.abs(player.getVelX() * 1.2f) * (1-rci.gettHitNear()));
				player.setVelY(player.getVelY() + rci.getContactNormalY() * Math.abs(player.getVelY() * 1.2f) * (1-rci.gettHitNear()));
			}
		}
		
		for (PhysicsObject po : physicsObjects) {
			po.moveBy(po.getVelX() * GameManager.getInstance().getDeltaTime(), po.getVelY() * GameManager.getInstance().getDeltaTime());
		}
	}
	
	//https://github.com/OneLoneCoder/Javidx9/blob/master/PixelGameEngine/SmallerProjects/OneLoneCoder_PGE_Rectangles.cpp
	/*
	 * bool PointVsRect(const olc::vf2d& p, const olc::aabb::rect* r)
			{
				return (p.x >= r->pos.x && p.y >= r->pos.y && p.x < r->pos.x + r->size.x && p.y < r->pos.y + r->size.y);
			}
	 */
	public static boolean PointVsRect(double pointX, double pointY, BoundingBox rect) {
		return (pointX >= rect.minX() &&
				pointY >= rect.minY() &&
				pointX < rect.maxX() &&
				pointY < rect.maxY());
	}
	
	/*
	 * bool RectVsRect(const olc::aabb::rect* r1, const olc::aabb::rect* r2)
			{
				return (r1->pos.x < r2->pos.x + r2->size.x && r1->pos.x + r1->size.x > r2->pos.x && r1->pos.y < r2->pos.y + r2->size.y && r1->pos.y + r1->size.y > r2->pos.y);
			}
	 */
	
	public static boolean RectVsRect(BoundingBox rect1, BoundingBox rect2) {
		return (rect1.minX() < rect2.maxX() &&
				rect1.maxX() > rect2.minX() &&
				rect1.minY() < rect2.maxY() &&
				rect1.maxY() > rect2.minY());
	}
	
	/*
	 * bool RayVsRect(const olc::vf2d& ray_origin, const olc::vf2d& ray_dir, const rect* target, olc::vf2d& contact_point, olc::vf2d& contact_normal, float& t_hit_near)
			{
				contact_normal = { 0,0 };
				contact_point = { 0,0 };

				// Cache division
				olc::vf2d invdir = 1.0f / ray_dir;

				// Calculate intersections with rectangle bounding axes
				olc::vf2d t_near = (target->pos - ray_origin) * invdir;
				olc::vf2d t_far = (target->pos + target->size - ray_origin) * invdir;

				if (std::isnan(t_far.y) || std::isnan(t_far.x)) return false;
				if (std::isnan(t_near.y) || std::isnan(t_near.x)) return false;

				// Sort distances
				if (t_near.x > t_far.x) std::swap(t_near.x, t_far.x);
				if (t_near.y > t_far.y) std::swap(t_near.y, t_far.y);

				// Early rejection		
				if (t_near.x > t_far.y || t_near.y > t_far.x) return false;

				// Closest 'time' will be the first contact
				t_hit_near = std::max(t_near.x, t_near.y);

				// Furthest 'time' is contact on opposite side of target
				float t_hit_far = std::min(t_far.x, t_far.y);

				// Reject if ray direction is pointing away from object
				if (t_hit_far < 0)
					return false;

				// Contact point of collision from parametric line equation
				contact_point = ray_origin + t_hit_near * ray_dir;

				if (t_near.x > t_near.y)
					if (invdir.x < 0)
						contact_normal = { 1, 0 };
					else
						contact_normal = { -1, 0 };
				else if (t_near.x < t_near.y)
					if (invdir.y < 0)
						contact_normal = { 0, 1 };
					else
						contact_normal = { 0, -1 };

				// Note if t_near == t_far, collision is principly in a diagonal
				// so pointless to resolve. By returning a CN={0,0} even though its
				// considered a hit, the resolver wont change anything.
				return true;
			}
	 */
	
	public static RayCollisionInfo RayVsRect(double rayOriginX, double rayOriginY, double rayDirX, double rayDirY, BoundingBox target) {
		double tNearX = (target.minX() - rayOriginX) / rayDirX;
		double tNearY = (target.minY() - rayOriginY) / rayDirY;
		double tFarX = (target.maxX() - rayOriginX) / rayDirX;
		double tFarY = (target.maxY() - rayOriginY) / rayDirY;
		
		if (tNearX > tFarX) {
			double temp = tNearX;
			tNearX = tFarX;
			tFarX = temp;
		}
		if (tNearY > tFarY) {
			double temp = tNearY;
			tNearY = tFarY;
			tFarY = temp;
		}
		
		if (tNearX > tFarY || tNearY > tFarX) {
			System.out.println("NOPE");
			return null;
		}
		
		double tHitNear = Math.max(tNearX,  tNearY);
		double tHitFar = Math.min(tFarX,  tFarY);
		
		//For collisions behind ray direction
		if (tHitFar < 0) 
			return null;
		
		RayCollisionInfo rci;
		double contactPointX = rayOriginX + tHitNear * rayDirX;
		double contactPointY = rayOriginY + tHitNear * rayDirY;
		double contactNormalX = 0, contactNormalY = 0;
		
		if (tNearX > tNearY) {
			if (rayDirX < 0) {
				contactNormalX = 1;
			} else {
				contactNormalX = -1;
			}
		} else if (tNearX < tNearY) {
			if (rayDirY < 0) {
				contactNormalY = 1;
			} else {
				contactNormalY = -1;
			}
		}
		
		return new RayCollisionInfo(contactPointX, contactPointY, contactNormalX, contactNormalY, tHitNear);
	}
	
	public static RayCollisionInfo DynamicRectVsRect(PhysicsObject srcRect, PhysicsObject target) {
		if (srcRect.getVelX() == 0 && srcRect.getVelY() == 0) {
			return null;
		}
		
		BoundingBox expanded_target = new BoundingBox(
				target.getHitbox().getWidth() + srcRect.getHitbox().getWidth(),
				target.getHitbox().getHeight() + srcRect.getHitbox().getHeight(),
				target.getXpos() + target.getHitbox().getxOffset(),
				target.getYpos() + target.getHitbox().getyOffset()
				);
		
		RayCollisionInfo rci = RayVsRect(srcRect.getXpos(), srcRect.getYpos(), srcRect.getVelX() * GameManager.getInstance().getDeltaTime(), srcRect.getVelY() * GameManager.getInstance().getDeltaTime(), expanded_target);
		if (rci != null) {
			if (rci.gettHitNear() <= 1) {
				return rci;
			}
		}
		
		return null;
	}
	
	/*#define OLC_PGE_APPLICATION
	#include "olcPixelGameEngine.h"
	#include <algorithm>
	#include <functional>
	#undef min
	#undef max

	namespace olc
	{
		namespace aabb
		{
			struct rect
			{
				olc::vf2d pos;
				olc::vf2d size;
				olc::vf2d vel;

				std::array<olc::aabb::rect*, 4> contact;
			};

			bool PointVsRect(const olc::vf2d& p, const olc::aabb::rect* r)
			{
				return (p.x >= r->pos.x && p.y >= r->pos.y && p.x < r->pos.x + r->size.x && p.y < r->pos.y + r->size.y);
			}

			 bool RectVsRect(const olc::aabb::rect* r1, const olc::aabb::rect* r2)
			{
				return (r1->pos.x < r2->pos.x + r2->size.x && r1->pos.x + r1->size.x > r2->pos.x && r1->pos.y < r2->pos.y + r2->size.y && r1->pos.y + r1->size.y > r2->pos.y);
			}

			bool RayVsRect(const olc::vf2d& ray_origin, const olc::vf2d& ray_dir, const rect* target, olc::vf2d& contact_point, olc::vf2d& contact_normal, float& t_hit_near)
			{
				contact_normal = { 0,0 };
				contact_point = { 0,0 };

				// Cache division
				olc::vf2d invdir = 1.0f / ray_dir;

				// Calculate intersections with rectangle bounding axes
				olc::vf2d t_near = (target->pos - ray_origin) * invdir;
				olc::vf2d t_far = (target->pos + target->size - ray_origin) * invdir;

				if (std::isnan(t_far.y) || std::isnan(t_far.x)) return false;
				if (std::isnan(t_near.y) || std::isnan(t_near.x)) return false;

				// Sort distances
				if (t_near.x > t_far.x) std::swap(t_near.x, t_far.x);
				if (t_near.y > t_far.y) std::swap(t_near.y, t_far.y);

				// Early rejection		
				if (t_near.x > t_far.y || t_near.y > t_far.x) return false;

				// Closest 'time' will be the first contact
				t_hit_near = std::max(t_near.x, t_near.y);

				// Furthest 'time' is contact on opposite side of target
				float t_hit_far = std::min(t_far.x, t_far.y);

				// Reject if ray direction is pointing away from object
				if (t_hit_far < 0)
					return false;

				// Contact point of collision from parametric line equation
				contact_point = ray_origin + t_hit_near * ray_dir;

				if (t_near.x > t_near.y)
					if (invdir.x < 0)
						contact_normal = { 1, 0 };
					else
						contact_normal = { -1, 0 };
				else if (t_near.x < t_near.y)
					if (invdir.y < 0)
						contact_normal = { 0, 1 };
					else
						contact_normal = { 0, -1 };

				// Note if t_near == t_far, collision is principly in a diagonal
				// so pointless to resolve. By returning a CN={0,0} even though its
				// considered a hit, the resolver wont change anything.
				return true;
			}

			bool DynamicRectVsRect(const olc::aabb::rect* r_dynamic, const float fTimeStep, const olc::aabb::rect& r_static,
				olc::vf2d& contact_point, olc::vf2d& contact_normal, float& contact_time)
			{
				// Check if dynamic rectangle is actually moving - we assume rectangles are NOT in collision to start
				if (r_dynamic->vel.x == 0 && r_dynamic->vel.y == 0)
					return false;

				// Expand target rectangle by source dimensions
				olc::aabb::rect expanded_target;
				expanded_target.pos = r_static.pos - r_dynamic->size / 2;
				expanded_target.size = r_static.size + r_dynamic->size;

				if (RayVsRect(r_dynamic->pos + r_dynamic->size / 2, r_dynamic->vel * fTimeStep, &expanded_target, contact_point, contact_normal, contact_time))
					return (contact_time >= 0.0f && contact_time < 1.0f);
				else
					return false;
			}



			bool ResolveDynamicRectVsRect(olc::aabb::rect* r_dynamic, const float fTimeStep, olc::aabb::rect* r_static)
			{
				olc::vf2d contact_point, contact_normal;
				float contact_time = 0.0f;
				if (DynamicRectVsRect(r_dynamic, fTimeStep, *r_static, contact_point, contact_normal, contact_time))
				{
					if (contact_normal.y > 0) r_dynamic->contact[0] = r_static; else nullptr;
					if (contact_normal.x < 0) r_dynamic->contact[1] = r_static; else nullptr;
					if (contact_normal.y < 0) r_dynamic->contact[2] = r_static; else nullptr;
					if (contact_normal.x > 0) r_dynamic->contact[3] = r_static; else nullptr;

					r_dynamic->vel += contact_normal * olc::vf2d(std::abs(r_dynamic->vel.x), std::abs(r_dynamic->vel.y)) * (1 - contact_time);
					return true;
				}

				return false;
			}
		}
	}


	class RectangleCollisions : public olc::PixelGameEngine
	{
	public:
		RectangleCollisions()
		{
			sAppName = "Rectangles!";
		}

		std::vector<olc::aabb::rect> vRects;

	public:
		bool OnUserCreate() override
		{
			vRects.push_back({ {170.0f, 70.0f}, {10.0f, 40.0f} });
			vRects.push_back({ {150.0f, 50.0f}, {20.0f, 20.0f} });
			vRects.push_back({ {150.0f, 150.0f}, {75.0f, 20.0f} });
			vRects.push_back({ {170.0f, 50.0f}, {20.0f, 20.0f} });
			vRects.push_back({ {190.0f, 50.0f}, {20.0f, 20.0f} });
			vRects.push_back({ {110.0f, 50.0f}, {20.0f, 20.0f} });
			vRects.push_back({ {50.0f, 130.0f}, {20.0f, 20.0f} });
			vRects.push_back({ {50.0f, 150.0f}, {20.0f, 20.0f} });
			vRects.push_back({ {50.0f, 170.0f}, {20.0f, 20.0f} });
			vRects.push_back({ {150.0f, 100.0f}, {10.0f, 1.0f} });
			vRects.push_back({ {200.0f, 100.0f}, {20.0f, 60.0f} });


			return true;
		}

		bool OnUserUpdate(float fElapsedTime) override
		{
			Clear(olc::DARK_BLUE);

			olc::vf2d vMouse = { float(GetMouseX()), float(GetMouseY()) };
			olc::vf2d vPoint = { 128.0f, 120.0f };

			if (GetKey(olc::Key::W).bHeld) vRects[0].vel.y = -100.0f;
			if (GetKey(olc::Key::S).bHeld) vRects[0].vel.y = +100.0f;
			if (GetKey(olc::Key::A).bHeld) vRects[0].vel.x = -100.0f;
			if (GetKey(olc::Key::D).bHeld) vRects[0].vel.x = +100.0f;

			if (GetMouse(0).bHeld)
				vRects[0].vel += (vMouse - vRects[0].pos).norm() * 100.0f * fElapsedTime;


			// Draw all rectangles
			for (const auto& r : vRects)
				DrawRect(r.pos, r.size, olc::WHITE);


			// Sort collisions in order of distance
			olc::vf2d cp, cn;
			float t = 0, min_t = INFINITY;
			std::vector<std::pair<int, float>> z;
	 
			// Work out collision point, add it to vector along with rect ID
			for (size_t i = 1; i < vRects.size(); i++)
			{
				if (olc::aabb::DynamicRectVsRect(&vRects[0], fElapsedTime, vRects[i], cp, cn, t))
				{
					z.push_back({ i, t });
				}
			}

			// Do the sort
			std::sort(z.begin(), z.end(), [](const std::pair<int, float>& a, const std::pair<int, float>& b)
				{
					return a.second < b.second;
				});

			// Now resolve the collision in correct order 
			for (auto j : z)
				olc::aabb::ResolveDynamicRectVsRect(&vRects[0], fElapsedTime, &vRects[j.first]);

			// Embellish the "in contact" rectangles in yellow
			for (int i = 0; i < 4; i++)
			{
				if (vRects[0].contact[i])
					DrawRect(vRects[0].contact[i]->pos, vRects[0].contact[i]->size, olc::YELLOW);
				vRects[0].contact[i] = nullptr;
			}

			// UPdate the player rectangles position, with its modified velocity
			vRects[0].pos += vRects[0].vel * fElapsedTime;
			
			// Draw players velocity vector
			if (vRects[0].vel.mag2() > 0)
				DrawLine(vRects[0].pos + vRects[0].size / 2, vRects[0].pos + vRects[0].size / 2 + vRects[0].vel.norm() * 20, olc::RED);

			return true;
		}
	};


	int main()
	{
		RectangleCollisions demo;
		if (demo.Construct(256, 240, 4, 4, false, false))
			demo.Start();
		return 0;
	}*/
}
