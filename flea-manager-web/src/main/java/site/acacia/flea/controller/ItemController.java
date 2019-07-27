package site.acacia.flea.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import site.acacia.flea.exception.NoPermission;
import site.acacia.flea.exception.Unauthorized;
import site.acacia.flea.pojo.EasyUIDataGridResult;
import site.acacia.flea.pojo.ItemDetail;
import site.acacia.flea.pojo.TbItem;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.ItemService;

/**
 * @author 张胤
 *
 *         2018年8月28日-上午10:46:27
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ItemController {

	@Autowired
	private ItemService itemService;

	protected static Logger logger = LoggerFactory.getLogger(ItemController.class);

	@RequestMapping("/user/item/detail")
	public ItemDetail getOItemDetailByItemId(HttpServletRequest request, String itemId) {
		logger.info("==> Request parameter: itemId(" + itemId + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
		} else {
			return itemService.getItemByItemId(itemId, user.getOpenid());
		}
		return itemService.getItemByItemId(itemId, "default");
	}

	/**
	 * 发布产品
	 * 
	 * @param item
	 *            商品信息
	 * @param buyTem
	 *            购买时间
	 * @param request
	 *            用户信息
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/user/item/save")
	public WeResult addItem(TbItem item, String buyTem, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: item(" + item + "),buyTem(" + buyTem + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			if (user.getUserStatus() == 2) {
				throw new NoPermission("403");
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date utilDate = null;
				utilDate = sdf.parse(buyTem);
				item.setOpenid(user.getOpenid());
				item.setBuyTime(utilDate);
				itemService.addItem(item);
				return WeResult.ok();
			}
		}
	}

	@PostMapping("/user/item/admin/save")
	public WeResult addItemAdmin(@RequestBody TbItem item, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: item(" + item + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			if (user.getUserStatus() == 2) {
				throw new NoPermission("403");
			} else {
				item.setOpenid(user.getOpenid());
				itemService.addItem(item);
				return WeResult.ok();
			}
		}
	}

	/**
	 * 
	 * @param page
	 * @param rows
	 * @param catId
	 * @param userId
	 * @param itemName
	 * @param startPrice
	 * @param endPrice
	 * @param schoolList
	 * @param status
	 * @param date
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/item/list")
	@ResponseBody
	public EasyUIDataGridResult<TbItem> getItems(int page, Integer rows,
			@RequestParam(value = "catId", required = false) Integer[] catId,
			@RequestParam(value = "userId", defaultValue = "") String userId,
			@RequestParam(value = "itemName", defaultValue = "") String itemName,
			@RequestParam(value = "startPrice", defaultValue = "") String startPrice,
			@RequestParam(value = "endPrice", defaultValue = "") String endPrice,
			@RequestParam(value = "schoolList", required = false) String[] schoolList,
			@RequestParam(value = "status", required = false) Byte[] status,
			@RequestParam(value = "Date", required = false) String[] date, HttpServletRequest request)
			throws Exception {
		logger.info("==> Request parameter: page(" + page + "),rows(" + rows + "),catId(" + catId + "),userId(" + userId
				+ ")," + "itemName(" + itemName + "),startPrice(" + startPrice + ")," + "endPrice(" + endPrice
				+ "),schoolList(" + Arrays.toString(schoolList) + "),status(" + Arrays.toString(status) + "),date("
				+ Arrays.toString(date) + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			if (user.getUserStatus() != 3) {
				throw new NoPermission("403");
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss Z", Locale.ENGLISH);
				Date utilDate[] = new Date[2];
				Long price[] = new Long[2];
				if (date != null) {
					try {
						utilDate[0] = sdf.parse(date[0]);
						utilDate[1] = sdf.parse(date[1]);
					} catch (ParseException e) {
						logger.error("<== 日期格式转换出错,原格式(" + date[0] + "," + date[1] + ")");
					}
				}
				if (StringUtils.isNotBlank(startPrice)) {
					price[0] = Math.round(Double.parseDouble(startPrice) * 100);
				} else {
					price[0] = (long) -1;
				}
				if (StringUtils.isNotBlank(endPrice)) {
					price[1] = Math.round(Double.parseDouble(endPrice) * 100);
				} else {
					price[1] = (long) -1;
				}
				return itemService.getItemList(page, rows, catId, userId, itemName, price[0], price[1], schoolList,
						status, utilDate[0], utilDate[1]);

			}
		}
	}

	/**
	 * 更新商品信息
	 * 
	 * @param request
	 * @param item
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/item/update")
	public WeResult updateItemByItemId(HttpServletRequest request, TbItem item) throws Exception {
		logger.info("==> Request parameter: item(" + item + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			if (user.getOpenid() != item.getOpenid()) {
				throw new NoPermission("403");
			} else {
				return itemService.updateItemByItemId(item);
			}
		}
	}

	/**
	 * 下架产品根据Id
	 *
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/item/instock")
	public WeResult instockItem(TbItem item, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: item(" + item + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			if (!user.getOpenid().equals(item.getOpenid()) && user.getUserStatus() != 3) {
				throw new NoPermission("403");
			} else {
				if (item.getStatus() == 3) {
					item.setItemDown(new Date());
					return itemService.updateItemByItemId(item);
				} else {
					item.setStatus((byte) 4);
					return itemService.updateItemByItemId(item);
				}
			}
		}
	}

	/**
	 * 上架产品根据Id
	 *
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/item/reshelf")
	public WeResult reshelfItem(String ids, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: ids(" + ids + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			if (user.getUserStatus() == 3) {
				throw new NoPermission("403");
			} else {
				TbItem item = new TbItem();
				item.setItemId(ids);
				item.setStatus((byte) 1);
				return itemService.updateItemByItemId(item);
			}
		}
	}

	/**
	 * 下架用户所有产品
	 *
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/item/allInstock")

	public WeResult instockAlItem(HttpServletRequest request, String userId) throws Exception {
		logger.info("==> Request parameter: userId(" + userId + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			if (user.getUserStatus() == 3) {
				throw new NoPermission("403");
			} else {
				return itemService.banItemByUserId(userId);
			}
		}
	}

}
